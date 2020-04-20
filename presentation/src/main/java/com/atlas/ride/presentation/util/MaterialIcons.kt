package com.atlas.ride.presentation.util

import android.content.Context
import android.graphics.Typeface

import com.atlas.ride.presentation.R
import com.atlas.ride.presentation.graphics.IIconFont

class MaterialIcons(context: Context) : IIconFont {
    override val typeface: Typeface = context.resources.getFont(R.font.material)

    override val names: List<String>
        get() = mappings.keys.toList()
    override val codepoints: List<Char>
        get() = mappings.values.toList()

    // The map is constructed from a raw resource containing name/codepoint pairs.
    private val mappings: Map<String, Char>

    init {
        val map = context.resources.openRawResource(R.raw.material_codepoints)
        mappings = map.bufferedReader().useLines {
            it.associate { line ->
                // Each line in the codepoint definition file is formatted as follows:
                // "<icon name> <unicode char in hexadecimal>"
                line.split(" ").let { (name, code) ->
                    val unicode = code.toInt(16).toChar()
                    name to unicode
                }
            }
        }

        map.close()
    }

    override fun getCodepoint(name: String): Char {
        if (name !in mappings)
            throw IndexOutOfBoundsException(
                "MaterialIcons contains no codepoint for key '${name}'."
            )

        return mappings.getValue(name)
    }
}

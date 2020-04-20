package com.atlas.ride.presentation.util

import android.content.Context
import android.graphics.Color

import com.atlas.ride.presentation.R
import com.atlas.ride.presentation.graphics.IPalette

class MaterialColors(context: Context) : IPalette {
    override val names: List<String>
        get() = mappings.keys.toList()
    override val colors: List<Int>
        get() = mappings.values.toList()

    // The map is constructed from a raw resource containing name/hexcode pairs.
    private val mappings: Map<String, Int>

    init {
        val map = context.resources.openRawResource(R.raw.material_colors)
        mappings = map.bufferedReader().useLines {
            it.associate { line ->
                // Each line in the codepoint definition file is formatted as follows:
                // "<icon name> <unicode char in hexadecimal>"
                line.split(" ").let { (name, code) ->
                    val color = Color.parseColor(code)
                    name to color
                }
            }
        }

        map.close()
    }

    override fun getColor(name: String): Int {
        if (name !in mappings)
            throw IndexOutOfBoundsException(
                "IconPalette contains no color for key '${name}'."
            )

        return mappings.getValue(name)
    }
}

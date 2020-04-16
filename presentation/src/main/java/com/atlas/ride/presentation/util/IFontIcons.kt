package com.atlas.ride.presentation.util

import android.graphics.Typeface

/**
 * The set of information needed to render icon from an icon font. This includes the typeface used,
 * as well as the names of available icons and their codepoint mappings.
 */
interface IFontIcons {
    /** The icon font, pre-loaded from the resources file. */
    val typeface: Typeface

    /** A list of human-readable names describing the icons. */
    val names: List<String>
    /** A list of unicode characters that actually render the icon in the font. */
    val codepoints: List<Char>

    /**
     * Retrieve a single unicode character based on the given icon name. If the given name does not
     * map to a valid codepoint, an exception is thrown.
     */
    fun getCodepoint(name: String): Char
}

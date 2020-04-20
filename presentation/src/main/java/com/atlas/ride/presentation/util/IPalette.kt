package com.atlas.ride.presentation.util

import android.graphics.Color

/**
 * A palette of colors, represented by names. Unlike the android `colors.xml` values, these colors
 * are meant to be dispayed and chosen by the user, hence they must be easily translatable from
 * between their names and their actual color codes.
 */
interface IPalette {
    /** The descriptive, human-readable name of each color. */
    val names: List<String>
    /** The loaded color values. */
    val colors: List<Int>

    /**
     * Retrieve a [Color] object that represents the given color name. If the given name does not
     * map to a color, an exception is thrown.
     */
    fun getColor(name: String): Int
}

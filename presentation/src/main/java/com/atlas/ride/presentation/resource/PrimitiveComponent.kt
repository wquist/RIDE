package com.atlas.ride.presentation.resource

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.core.graphics.withScale

import kotlin.math.max

/**
 * A utility class that handles the actual drawing of a primitive symbol and background. This allows
 * for a common implementation amongst things, relationships, etc. The component only stores values
 * that do not change across drawing multiple primitives (such as the radius).
 */
open class PrimitiveComponent(var radius: Int, var minRadius: Int = 0) {
    /**
     * Draw the primitive color and symbol to the given canvas, using the stored radius.
     */
    fun draw(canvas: Canvas, icon: Drawable, background: Paint) {
        val r = max(radius, minRadius).toFloat()
        val iconSize = r * 2f / icon.bounds.height()

        with(canvas) {
            drawCircle(r, r, r, background)
            withScale(iconSize * 0.6f, iconSize * 0.6f) {
                icon.draw(this)
            }
        }
    }
}

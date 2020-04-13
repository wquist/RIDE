package com.atlas.ride.presentation.resource

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.core.graphics.withScale
import androidx.core.graphics.withTranslation

import kotlin.math.PI
import kotlin.math.max
import kotlin.math.sin

/**
 * A utility class that handles the actual drawing of a thing symbol (namely, the icon, circle
 * background, and status in the corner). This allows for a common implementation amongst things,
 * relationships, etc. The component only stores values that do not change across drawing multiple
 * things (the radius and stroke color, in this case).
 */
class ThingComponent(var radius: Int, var minRadius: Int = 0) {
    private val strokePaint = Paint().apply {
        color = Color.WHITE
        flags = Paint.ANTI_ALIAS_FLAG
    }

    /**
     * Draw the thing symbol to the given canvas, using the stored radius.
     */
    fun draw(canvas: Canvas, icon: Drawable, background: Paint, status: Paint) {
        val r = max(radius, minRadius).toFloat()
        val iconSize = r * 2f / icon.bounds.height()
        val statusOffset = r * sin(45 * PI / 180).toFloat()

        with(canvas) {
            drawCircle(r, r, r, background)
            withScale(iconSize * 0.6f, iconSize * 0.6f) {
                icon.draw(this)
            }

            withTranslation(r, r) {
                drawCircle(statusOffset, -statusOffset, r * 0.30f, strokePaint)
                drawCircle(statusOffset, -statusOffset, r * 0.25f, status)
            }
        }
    }
}

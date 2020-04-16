package com.atlas.ride.presentation.resource

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.core.graphics.withTranslation

import kotlin.math.PI
import kotlin.math.max
import kotlin.math.sin

/**
 * A utility class that handles the actual drawing of a thing on top of a primitive. This adds a
 * "status" circle in the top right of the primitive component that can be changed colors to
 * reflect the operating state of the thing.
 */
class ThingComponent(radius: Int, minRadius: Int = 0): PrimitiveComponent(radius, minRadius) {
    // The stroke is used to separate the status circle from the background.
    private val strokePaint = Paint().apply {
        color = Color.WHITE
        flags = Paint.ANTI_ALIAS_FLAG
    }

    /**
     * Draw the primitive along with a status identifier.
     */
    fun draw(canvas: Canvas, icon: Drawable, background: Paint, status: Paint) {
        val r = max(radius, minRadius).toFloat()
        val statusOffset = r * sin(45 * PI / 180).toFloat()

        super.draw(canvas, icon, background)
        canvas.withTranslation(r, r) {
            drawCircle(statusOffset, -statusOffset, r * 0.30f, strokePaint)
            drawCircle(statusOffset, -statusOffset, r * 0.25f, status)
        }
    }
}

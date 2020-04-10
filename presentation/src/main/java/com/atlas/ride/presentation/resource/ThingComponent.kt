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

class ThingComponent(val radius: Int, val minRadius: Int = 0) {
    private val strokePaint = Paint().apply {
        color = Color.WHITE
        flags = Paint.ANTI_ALIAS_FLAG
    }

    fun draw(canvas: Canvas, icon: Drawable, background: Paint, status: Paint) {
        val r = max(radius, minRadius).toFloat()
        val s = r * 2f / icon.bounds.height()
        val d = r * sin(45 * PI / 180).toFloat()

        with(canvas) {
            drawCircle(r, r, r, background)
            withScale(s * 0.6f, s * 0.6f) {
                icon.draw(this)
            }

            withTranslation(r, r) {
                drawCircle(d, -d, r * 0.30f, strokePaint)
                drawCircle(d, -d, r * 0.25f, status)
            }
        }
    }
}
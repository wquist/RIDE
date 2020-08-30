package com.atlas.ride.presentation.graphics

import android.graphics.*
import android.graphics.drawable.Drawable

import kotlin.math.min

/**
 * A wrapper class to allow simple primitives to be rendered within image view, etc.
 */
open class PrimitiveDrawable(ic: Drawable) : Drawable() {
    /** The primitive icon (note that the color is not set). */
    var icon: Drawable = ic
        set(value) { field = value; invalidateSelf() }
    /** The color of the background circle. */
    var color: Int
        get() = paint.color
        set(value) { paint.color = value; invalidateSelf() }

    protected open val component = PrimitiveComponent(0)
    protected val paint = Paint().apply {
        color = Color.BLACK
        flags = Paint.ANTI_ALIAS_FLAG
    }

    /**
     * Draw the primitive component with the current bounds and graphic information.
     */
    override fun draw(canvas: Canvas) {
        component.draw(canvas, icon, paint)
    }

    /**
     * The primitive is always drawn within a square. If the bounds are rectangular, the smallest
     * dimension is used as the radius.
     */
    override fun onBoundsChange(bounds: Rect?) {
        super.onBoundsChange(bounds)

        bounds?.let {
            val size = min(it.width(), it.height())
            component.radius = size / 2

            invalidateSelf()
        }
    }

    /**
     * The child drawables must be updated here, so that the bounds always match, even when a child
     * element is first set.
     */
    override fun invalidateSelf() {
        super.invalidateSelf()
        icon.bounds = bounds
    }

    override fun setAlpha(alpha: Int) = throw UnsupportedOperationException(
        "PrimitiveDrawable does not support alpha values other than opaque."
    )
    override fun setColorFilter(colorFilter: ColorFilter?) = throw UnsupportedOperationException(
        "PrimitiveDrawable does not support color filters."
    )

    /** [Drawable.getOpacity] is deprecated, but still required. */
    override fun getOpacity(): Int = PixelFormat.OPAQUE
}

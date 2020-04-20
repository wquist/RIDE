package com.atlas.ride.presentation.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet

import kotlin.math.min

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.presentation.graphics.ThingComponent

/**
 * A view to display an iconic representation of a single [IThing] object. This draws the thing as
 * describes in [ThingComponent] (a colored circle with icon and status).
 */
class ThingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : PrimitiveView(context, attrs, defStyle) {
    /** The center icon, initially transparent until set. */
    var icon: Drawable = ColorDrawable(0)
    /** The color of the background circle. */
    var color: Int
        get() = colorPaint.color
        set(value) { colorPaint.color = value; invalidate() }
    /** The thing status, which maps to the color of the small circle based on the state list. */
    var status = IThing.Status.UNKNOWN
        set(value) { field = value; invalidate() }

    private val component = ThingComponent(0)
    private val colorPaint = Paint().apply {
        color = Color.BLACK
        flags = Paint.ANTI_ALIAS_FLAG
    }
    private val statusPaint = Paint().apply {
        color = Color.BLACK
        flags = Paint.ANTI_ALIAS_FLAG
    }

    /**
     * This method is called by the primitive base when the status or its color list is updated.
     * Other changes should be reflected automatically when redrawn.
     */
    override fun invalidate() {
        super.invalidate()
        statusPaint.color = statusColorList.getColorForState(status.toStateList(), Color.BLACK)
    }

    /**
     * A thing view has no size constraints; it will always draw the icon within the smallest
     * dimension (width or height) of the view.
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val size = min(w, h)
        component.radius = size / 2
    }

    /**
     * The thing is always drawable; without a specified icon, color, or status, it will render as
     * a single black circle with a black status.
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            component.draw(it, icon, colorPaint, statusPaint)
        }
    }
}

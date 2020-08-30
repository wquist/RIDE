package com.atlas.ride.presentation.graphics

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.presentation.R

/**
 * A wrapper class to render thing representations through the [Drawable] interface. This extends
 * [PrimitiveDrawable] and adds a status bubble in the corner of the background circle.
 */
class ThingDrawable(ic: Drawable, private val statusColor: ColorStateList) : PrimitiveDrawable(ic) {
    /**
     * Provide a factory to simplify creation of a [ThingDrawable]. The factory loads the status
     * colors from the given context and handles converting image names into the appropriate inner
     * thing icon. This allows drawables to be created directly from the info in [IThing].
     */
    class Factory(context: Context, private val font: IIconFont) {
        // Load the color state list once from its styled context attribute.
        private val statusColor: ColorStateList

        init {
            @Suppress // Ignore the warning about recycling, which is erroneous.
            context.obtainStyledAttributes(intArrayOf(R.attr.thing_statusColor)).also {
                // If not set, use an empty state list which later resolves to [Color.Black].
                val defaultColor = ColorStateList(arrayOf<IntArray>(), intArrayOf())
                statusColor = it.getColorStateList(0) ?: defaultColor
            }.recycle()
        }

        /**
         * Create a drawable from a valid [IThing] instance.
         */
        fun createDrawable(thing: IThing): ThingDrawable {
            val icon = FontDrawable(font.typeface)
            icon.text = font.getCodepoint(thing.icon).toString()

            return ThingDrawable(icon, statusColor).apply {
                color  = thing.color
                status = thing.status
            }
        }
    }

    /** The current status of the thing (reflected in the small upper-right circle. */
    var status: IThing.Status = IThing.Status.UNKNOWN
        set(value) { field = value; invalidateSelf() }

    // A [ThingComponent] is needed to render the status as well.
    override val component = ThingComponent(0)
    // The status paint represents the active color from [statusColor].
    private val statusPaint = Paint().apply {
        flags = Paint.ANTI_ALIAS_FLAG
    }

    init {
        // Set up the initial status color.
        invalidateSelf()
    }

    /**
     * Draw the component as a thing instead of a primitive (for the status bubble).
     */
    override fun draw(canvas: Canvas) {
        component.draw(canvas, icon, paint, statusPaint)
    }

    /**
     * Retrieving the appropriate status color is done during every invalidation.
     */
    override fun invalidateSelf() {
        super.invalidateSelf()

        statusPaint.color = statusColor.getColorForState(when (status) {
            IThing.Status.UNKNOWN -> intArrayOf(R.attr.state_status_unknown)
            IThing.Status.PROBLEM -> intArrayOf(R.attr.state_status_problem)
            IThing.Status.WORKING -> intArrayOf(R.attr.state_status_working)
        }, Color.BLACK)
    }
}

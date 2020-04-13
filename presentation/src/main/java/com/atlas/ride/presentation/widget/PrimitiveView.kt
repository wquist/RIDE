package com.atlas.ride.presentation.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.presentation.R

/**
 * A base class to handle common functionality between thing, relationship, and recipe views. This
 * includes managing the colors for the different thing statuses (which appear in every view since
 * they all build on top of things).
 */
abstract class PrimitiveView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {
    /** The three status colors. Any unspecified color state will resolve to black. */
    var statusColorList = ColorStateList(arrayOf<IntArray>(), intArrayOf())
        set(value) { field = value; invalidate() }

    /**
     * [ColorStateList] operates on an [IntArray], although [IThing.Status] represents only a single
     * color at a given time. This simplifies use when retrieving the current status color.
     */
    internal fun IThing.Status.toStateList() = when (this) {
        IThing.Status.UNKNOWN -> intArrayOf(R.attr.state_status_unknown)
        IThing.Status.PROBLEM -> intArrayOf(R.attr.state_status_problem)
        IThing.Status.WORKING -> intArrayOf(R.attr.state_status_working)
    }
}

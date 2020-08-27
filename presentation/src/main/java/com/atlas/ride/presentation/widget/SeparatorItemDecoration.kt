package com.atlas.ride.presentation.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State

import com.atlas.ride.presentation.R

/**
 * Decorate [RecyclerView] list items with a "separator". Note that unlike the built-in "divider"
 * item decoration, the separator only appears between items; that is, no separator is rendered
 * after the last item. The visibility of the separator can also be controlled through the view tag
 * [R.id.tag_hasSeparator]. The separator is rendered unless this tag is set to false.
 */
class SeparatorItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    /** Additional padding (added to view padding) can be specified with the following members. */
    var paddingLeft = 0
    var paddingRight = 0

    // The drawable is gathered from the system attribute "listDivider".
    private val drawable: Drawable

    init {
        @Suppress // Ignore the warning about recycling, which is erroneous.
        context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider)).also {
            // Use a black line if no divider styling is present.
            drawable = it.getDrawable(0) ?: ColorDrawable(Color.BLACK)
        }.recycle()
    }

    /**
     * Specify additional padding for a divider only when one should be present; un-separated item
     * views should be rendered with their native padding only.
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        if (viewNeedsSeparator(view, parent))
            outRect.set(0, 0, 0, drawable.intrinsicHeight)
        else
            outRect.setEmpty()
    }

    /**
     * Render each separator only if space was created for it. The separator respects the padding of
     * the item view, and adds on its own, if any.
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {
        val left = parent.paddingLeft + paddingLeft
        val right = parent.width - (parent.paddingRight + paddingRight)

        for (child in parent.children) {
            if (!viewNeedsSeparator(child, parent))
                continue

            val top = child.bottom
            val bottom = top + drawable.intrinsicHeight

            drawable.setBounds(left, top, right, bottom)
            drawable.draw(c)
        }
    }

    // Specify the conditions for separators, to be used by both [getItemOffsets] and [onDraw].
    private fun viewNeedsSeparator(view: View, parent: RecyclerView): Boolean {
        // The last item in the list requires no separator.
        val adapter = parent.adapter ?: return false
        if (parent.getChildAdapterPosition(view) == adapter.itemCount - 1)
            return false

        // The separator is rendered by default, unless explicitly disabled.
        val tag = view.getTag(R.id.tag_hasSeparator) as? Boolean?
        if (tag != null && tag == false)
            return false

        return true
    }
}

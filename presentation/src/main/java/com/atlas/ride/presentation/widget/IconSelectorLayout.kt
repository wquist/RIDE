package com.atlas.ride.presentation.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.constraintlayout.widget.ConstraintLayout

import kotlinx.android.synthetic.main.content_icon.view.*

import com.atlas.ride.presentation.R

/**
 * A layout allowing customization of thing/app icons. An icon consists of an image drawable and a
 * color, both of which can be selected from the dropdowns within the layout. The choices are
 * provided through a custom adapter, which also handles generation of the final icon preview.
 */
class IconSelectorLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    /**
     * The icon selector adapter provides the dropdown data and handles the [Drawable] generation.
     * When either dropdown field is changed, the [getPreview] method is invoked with the current
     * field values.
     */
    interface Adapter {
        /** The list of available icon image names. */
        val imageEntries: List<String>
        /** The list of available color names. */
        val colorEntries: List<String>

        /** Create a drawable based on the active icon image and color names. */
        fun getPreview(image: String, color: String): Drawable
    }

    /** The active adapter. If null, the dropdowns will be empty and no icon will be displayed. */
    var adapter: Adapter? = null
        set(value) { field = value; notifyDataSetChanged() }

    // Each dropdown must have its own adapter, which is created from the main one.
    private val imageAdapter = ArrayAdapter<String>(context, android.R.layout.select_dialog_item)
    private val colorAdapter = ArrayAdapter<String>(context, android.R.layout.select_dialog_item)

    init {
        inflate(context, R.layout.content_icon, this)
    }

    /**
     * Setup the initial layout state, and listen for changes in the dropdowns.
     */
    override fun onFinishInflate() {
        super.onFinishInflate()

        icon_image_text.setAdapter(imageAdapter)
        icon_color_text.setAdapter(colorAdapter)

        icon_image_text.setOnItemClickListener { _, _, _, _ ->
            updatePreview()
        }
        icon_color_text.setOnItemClickListener { _, _, _, _ ->
            updatePreview()
        }
    }

    /**
     * The main adapter has changed, the dropdown data must be updated and the icon regenerated.
     */
    private fun notifyDataSetChanged() {
        imageAdapter.clear()
        colorAdapter.clear()

        adapter?.let {
            if (it.imageEntries.isEmpty() || it.colorEntries.isEmpty()) {
                throw IllegalStateException(
                    "IconSelectorLayout adapter must have at least one color and image choice."
                )
            }

            imageAdapter.addAll(it.imageEntries)
            colorAdapter.addAll(it.colorEntries)

            icon_image_text.setText(it.imageEntries.first(), false)
            icon_color_text.setText(it.colorEntries.first(), false)
            updatePreview()
        }
    }

    /**
     * Use the current values of the dropdowns to request a new preview drawable be generated.
     */
    private fun updatePreview() {
        val image = icon_image_text.text.toString()
        val color = icon_color_text.text.toString()

        adapter?.let {
            val preview = it.getPreview(image, color)
            icon_preview_view.setImageDrawable(preview)
        }
    }
}

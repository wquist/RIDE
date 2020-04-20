package com.atlas.ride.presentation.util

import android.graphics.drawable.Drawable

import com.atlas.ride.presentation.R
import com.atlas.ride.presentation.graphics.FontDrawable
import com.atlas.ride.presentation.graphics.IIconFont
import com.atlas.ride.presentation.graphics.IPalette
import com.atlas.ride.presentation.graphics.PrimitiveDrawable
import com.atlas.ride.presentation.widget.IconSelectorLayout

class IconAdapter(
    private val font: IIconFont,
    private val palette: IPalette
) : IconSelectorLayout.Adapter {
    override val imageEntries: List<String>
        get() = font.names
    override val colorEntries: List<String>
        get() = palette.names

    private val icon = FontDrawable(font.typeface)
    private val drawable = PrimitiveDrawable(icon)

    override fun onAttachedToIconSelectorLayout(layout: IconSelectorLayout) {
        icon.color = layout.context.getColor(R.color.white)
    }

    override fun getPreview(image: String, color: String): Drawable {
        icon.text = font.getCodepoint(image).toString()
        drawable.color = palette.getColor(color)

        return drawable
    }
}

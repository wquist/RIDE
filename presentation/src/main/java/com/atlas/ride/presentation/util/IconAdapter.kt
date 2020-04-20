package com.atlas.ride.presentation.util

import android.graphics.Color
import android.graphics.drawable.Drawable

import com.atlas.ride.presentation.resource.FontDrawable
import com.atlas.ride.presentation.resource.PrimitiveDrawable
import com.atlas.ride.presentation.widget.IconSelectorLayout

class IconAdapter(
    private val font: IIconFont,
    private val palette: IPalette
) : IconSelectorLayout.Adapter {
    override val imageEntries: List<String>
        get() = font.names
    override val colorEntries: List<String>
        get() = palette.names

    private val icon = FontDrawable(font.typeface).apply {
        color = Color.WHITE
    }
    private val drawable = PrimitiveDrawable(icon)

    override fun getPreview(image: String, color: String): Drawable {
        icon.text = font.getCodepoint(image).toString()
        drawable.color = palette.getColor(color)

        return drawable
    }
}

package com.atlas.ride.presentation.resource

import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint

/**
 * A drawable that allows rendering a string in the given [Typeface]. Unlike a normal TextView, this
 * specically targets rendering single glyphs from icons fonts. The icon is rendered centered within
 * the drawable bounds as large as the height allows.
 */
class FontDrawable(val typeface: Typeface) : Drawable() {
    /** The text to render. Should be a single Unicode escape sequence. */
    var text = ""
        set(value) { field = value; invalidateSelf() }
    /** The size of the icon bounds, in pixels. */
    var size: Int
        get() = bounds.height()
        set(value) { setBounds(0, 0, value, value); invalidateSelf() }
    /** The color to render the icon glyph with. */
    var color: Int
        get() = paint.color
        set(value) { paint.color = value; invalidateSelf() }

    private val paint = TextPaint().apply {
        typeface = this@FontDrawable.typeface
        textAlign = Paint.Align.CENTER
        color = Color.WHITE
        flags = Paint.ANTI_ALIAS_FLAG
    }

    /** Draw the specified text. Strings with multiple characters may not render properly. */
    override fun draw(canvas: Canvas) {
        val textBounds = Rect()
        paint.getTextBounds(text, 0, 1, textBounds)

        val baseline = (bounds.height() - textBounds.height()) / 2f - textBounds.top
        canvas.drawText(text, bounds.width() / 2f, baseline, paint)
    }

    /** The target text size must be updated when the drawable bounds are changed. */
    override fun invalidateSelf() {
        super.invalidateSelf()
        paint.textSize = size.toFloat()
    }

    override fun setAlpha(alpha: Int) = throw UnsupportedOperationException(
        "FontDrawable does not support alpha values other than opaque."
    )
    override fun setColorFilter(colorFilter: ColorFilter?) = throw UnsupportedOperationException(
        "FontDrawable does not support color filters."
    )

    /** [Drawable.getOpacity] is deprecated, but still required. */
    override fun getOpacity(): Int = PixelFormat.OPAQUE
}

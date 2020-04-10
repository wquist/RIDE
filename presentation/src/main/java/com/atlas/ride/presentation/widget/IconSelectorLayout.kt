package com.atlas.ride.presentation.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

import com.atlas.ride.presentation.R

class IconSelectorLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    init {
        inflate(context, R.layout.content_icon, this)
    }
}

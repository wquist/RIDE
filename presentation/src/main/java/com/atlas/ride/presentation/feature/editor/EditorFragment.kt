package com.atlas.ride.presentation.feature.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.atlas.ride.presentation.R

class EditorFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_editor, parent, false)
    }
}

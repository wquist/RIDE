package com.atlas.ride.presentation.feature.editor

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.atlas.ride.presentation.R

class ToolboxFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_toolbox, parent, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbox, menu)
    }
}

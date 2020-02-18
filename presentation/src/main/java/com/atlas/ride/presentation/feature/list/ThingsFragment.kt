package com.atlas.ride.presentation.feature.list

import android.os.Bundle
import android.view.*

import com.atlas.ride.presentation.R

class ThingsFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_things, parent, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        createSortSubMenu(R.menu.sort_things, menu, inflater)
    }
}

package com.atlas.ride.presentation.feature.list

import android.os.Bundle
import android.view.*

import org.koin.androidx.viewmodel.ext.android.viewModel

import com.atlas.ride.domain.util.Lce
import com.atlas.ride.presentation.R

class ThingsFragment : BaseFragment() {
    private val model: ThingsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_things, parent, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        createSortSubMenu(R.menu.sort_things, menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.state.observe(this) {
            when (it) {
                is Lce.Content -> {}
            }
        }
    }
}

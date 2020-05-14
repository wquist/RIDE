package com.atlas.ride.presentation.feature.list

import android.os.Bundle
import android.view.*

import org.koin.androidx.viewmodel.ext.android.viewModel

import com.atlas.ride.domain.util.Lce
import com.atlas.ride.presentation.R

class ThingsFragment : BaseFragment(R.layout.fragment_things, R.menu.sort_things) {
    private val model: ThingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.state.observe(this) {
            when (it) {
                is Lce.Content -> {}
            }
        }
    }
}

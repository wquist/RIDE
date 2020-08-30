package com.atlas.ride.presentation.feature.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.fragment_things.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.domain.util.Lce
import com.atlas.ride.presentation.R
import com.atlas.ride.presentation.graphics.ThingDrawable
import com.atlas.ride.presentation.widget.SeparatorItemDecoration

class ThingsFragment : BaseFragment(R.layout.fragment_things, R.menu.sort_things) {
    private val model: ThingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ThingsAdapter(ThingDrawable.Factory(requireContext(), get()))
        with (things_recycler_view) {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter

            addItemDecoration(SeparatorItemDecoration(context))
        }

        model.state.observe(this) {
            when (it) {
                is Lce.Content -> adapter.submitList(it.data as List<IThing>, { thing -> thing.status }) { status ->
                    when (status) {
                        IThing.Status.UNKNOWN -> "Offline"
                        IThing.Status.PROBLEM -> "Partially Operational"
                        IThing.Status.WORKING -> "Online"
                    }
                }
            }
        }
    }
}

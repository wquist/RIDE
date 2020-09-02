package com.atlas.ride.presentation.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

import com.atlas.ride.domain.util.Lce
import com.atlas.ride.presentation.R

class ThingDetailsFragment : Fragment() {
    private val args: ThingDetailsFragmentArgs by navArgs()
    private val model: ThingDetailsViewModel by viewModel { parametersOf(args.id) }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_thingdetails, parent, false)
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

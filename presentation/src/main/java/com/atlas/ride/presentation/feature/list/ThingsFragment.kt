package com.atlas.ride.presentation.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.atlas.ride.presentation.R

class ThingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_things, parent, false)
    }
}

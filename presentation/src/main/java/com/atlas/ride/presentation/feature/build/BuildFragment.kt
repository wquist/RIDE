package com.atlas.ride.presentation.feature.build

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.fragment_build.*
import org.koin.android.ext.android.get

import com.atlas.ride.presentation.R
import com.atlas.ride.presentation.util.IconAdapter

class BuildFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_build, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = IconAdapter(get(), get())
        build_icon_selector.adapter = adapter
    }
}

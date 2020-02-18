package com.atlas.ride.presentation.feature.notifications

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.atlas.ride.presentation.R

class NotificationsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_notifications, parent, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.notifications, menu)
    }
}

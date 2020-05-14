package com.atlas.ride.presentation.feature.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.atlas.ride.presentation.R

abstract class BaseFragment(private val layoutId: Int, private val menuId: Int) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(layoutId, parent, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list, menu)

        val sort = menu.findItem(R.id.list_sort_item).subMenu
        inflater.inflate(menuId, sort)
    }
}

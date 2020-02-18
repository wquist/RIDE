package com.atlas.ride.presentation.feature.list

import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment

import com.atlas.ride.presentation.R

abstract class BaseFragment : Fragment() {
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list, menu)
    }

    protected fun createSortSubMenu(subMenu: Int, menu: Menu, inflater: MenuInflater) {
        val sort = menu.findItem(R.id.list_sort_item).subMenu
        inflater.inflate(subMenu, sort)
    }
}
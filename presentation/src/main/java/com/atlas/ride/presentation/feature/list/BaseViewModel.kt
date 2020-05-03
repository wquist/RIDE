package com.atlas.ride.presentation.feature.list

import androidx.lifecycle.ViewModel

import com.atlas.ride.domain.util.Lce
import com.atlas.ride.presentation.util.ViewState

abstract class BaseViewModel : ViewModel() {
    val state = ViewState<Lce<List<*>>>(Lce.Loading)
}

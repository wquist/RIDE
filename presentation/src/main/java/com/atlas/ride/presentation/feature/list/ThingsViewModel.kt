package com.atlas.ride.presentation.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.domain.interact.GetThingsInteractor
import com.atlas.ride.domain.repo.IThingsRepository
import com.atlas.ride.domain.util.Lce
import com.atlas.ride.presentation.util.ViewState

class ThingsViewModel(repo: IThingsRepository) : ViewModel() {
    val state = ViewState<Lce<List<IThing>>>(Lce.Loading)

    init {
        viewModelScope.launch {
            GetThingsInteractor(repo).invoke { state.value = it }
        }
    }
}

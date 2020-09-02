package com.atlas.ride.presentation.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.domain.interact.GetThingDataInteractor
import com.atlas.ride.domain.repo.IThingsRepository
import com.atlas.ride.domain.util.Lce
import com.atlas.ride.presentation.util.ViewState

class ThingDetailsViewModel(repo: IThingsRepository, id: Int) : ViewModel() {
    val state = ViewState<Lce<IThing>>(Lce.Loading)

    init {
        viewModelScope.launch {
            GetThingDataInteractor(repo, id).invoke { state.value = it }
        }
    }
}

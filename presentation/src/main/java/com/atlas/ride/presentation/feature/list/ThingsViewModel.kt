package com.atlas.ride.presentation.feature.list

import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

import com.atlas.ride.domain.interact.GetThingsInteractor
import com.atlas.ride.domain.repo.IThingsRepository

class ThingsViewModel(repo: IThingsRepository) : BaseViewModel() {
    init {
        viewModelScope.launch {
            GetThingsInteractor(repo).invoke { state.value = it }
        }
    }
}

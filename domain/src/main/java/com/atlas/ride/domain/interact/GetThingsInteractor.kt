package com.atlas.ride.domain.interact

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.domain.repo.IThingsRepository
import com.atlas.ride.domain.util.Lce

class GetThingsInteractor(private val repo: IThingsRepository) : BaseInteractor<List<IThing>>() {
    override suspend operator fun invoke(onChange: (Lce<List<IThing>>) -> Unit) {
        onChange(Lce.Loading)
        repo.get()
            .catch { e ->
                val msg = e.message ?: "GetThingsInteractor could not retrieve thing info."
                onChange(Lce.Error(msg))
            }
            .collect {
                onChange(Lce.Content(it))
            }
    }
}

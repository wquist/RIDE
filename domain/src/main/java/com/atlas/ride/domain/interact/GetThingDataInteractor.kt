package com.atlas.ride.domain.interact

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.domain.repo.IThingsRepository
import com.atlas.ride.domain.util.Lce

class GetThingDataInteractor(
    private val repo: IThingsRepository,
    private val id: Int
) : BaseInteractor<IThing>() {
    override suspend operator fun invoke(onChange: (Lce<IThing>) -> Unit) {
        onChange(Lce.Loading)
        repo.get(id)
            .catch { e ->
                val msg = e.message ?: "GetThingDataInteractor could not retrieve thing info."
                onChange(Lce.Error(msg))
            }
            .collect {
                onChange(Lce.Content(it))
            }
    }
}

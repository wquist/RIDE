package com.atlas.ride.domain.repo

import kotlinx.coroutines.flow.Flow

import com.atlas.ride.domain.entity.IThing

interface IThingsRepository {
    fun get(): Flow<List<IThing>>
}

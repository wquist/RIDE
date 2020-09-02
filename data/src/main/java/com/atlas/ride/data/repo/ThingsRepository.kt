package com.atlas.ride.data.repo

import kotlinx.coroutines.flow.Flow

import com.atlas.ride.data.persist.PrimitivesDatabase
import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.domain.repo.IThingsRepository

class ThingsRepository(private val db: PrimitivesDatabase) : IThingsRepository {
    override fun get(): Flow<List<IThing>> = db.thingDao().get()
    override fun get(id: Int): Flow<IThing> = db.thingDao().get(id)
}

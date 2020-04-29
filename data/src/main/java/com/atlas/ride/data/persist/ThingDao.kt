package com.atlas.ride.data.persist

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

import kotlinx.coroutines.flow.Flow

import com.atlas.ride.data.entity.Thing

@Dao
abstract class ThingDao : BaseDao<Thing.Fields>() {
    @Query("SELECT * FROM Things ORDER BY name")
    abstract fun get(): Flow<List<Thing.Fields>>

    @Transaction @Query("SELECT * FROM Things WHERE thingId = :id")
    abstract fun get(id: Int): Flow<Thing>
}

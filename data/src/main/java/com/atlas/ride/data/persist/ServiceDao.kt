package com.atlas.ride.data.persist

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

import kotlinx.coroutines.flow.Flow

import com.atlas.ride.data.entity.Service

@Dao
abstract class ServiceDao : BaseDao<Service.Fields>() {
    @Transaction @Query("SELECT * FROM Services ORDER BY name")
    abstract fun get(): Flow<List<Service.AllFields>>

    @Transaction @Query("SELECT * FROM Services WHERE serviceId = :id")
    abstract fun get(id: Int): Flow<Service>
}

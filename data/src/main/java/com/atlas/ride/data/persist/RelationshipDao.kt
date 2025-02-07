package com.atlas.ride.data.persist

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

import kotlinx.coroutines.flow.Flow

import com.atlas.ride.data.entity.Relationship

@Dao
abstract class RelationshipDao : BaseDao<Relationship.Fields>() {
    @Transaction @Query("SELECT * FROM Relationships ORDER BY name")
    abstract fun get(): Flow<List<Relationship.AllFields>>

    @Transaction @Query("SELECT * FROM Relationships WHERE relationshipId = :id")
    abstract fun get(id: Int): Flow<Relationship>
}

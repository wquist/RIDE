package com.atlas.ride.data.persist

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(row: T)

    @Update
    abstract suspend fun update(row: T)

    @Delete
    abstract suspend fun delete(row: T)
}

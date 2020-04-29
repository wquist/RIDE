package com.atlas.ride.data.persist

import androidx.room.Dao

import com.atlas.ride.data.entity.Resource

@Dao
abstract class ResourceDao : BaseDao<Resource.Fields>()

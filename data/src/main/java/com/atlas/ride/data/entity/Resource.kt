package com.atlas.ride.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.atlas.ride.domain.entity.IPrimitive
import com.atlas.ride.domain.entity.IResource

@Entity(tableName = "Resources")
data class Resource(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    override val type: IPrimitive.Type,

    override val icon: String,
    override val color: Int
) : IResource

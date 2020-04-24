package com.atlas.ride.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

import com.atlas.ride.domain.entity.IThing

@Entity(tableName = "Things")
class Thing(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @Ignore
    override val services: List<Service>,

    override val name: String,
    override val description: String,

    override val icon: String,
    override val color: Int,

    override val status: IThing.Status
) : IThing

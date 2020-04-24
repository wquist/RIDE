package com.atlas.ride.data.entity

import androidx.room.Entity
import androidx.room.Ignore

import com.atlas.ride.domain.entity.IPrimitive
import com.atlas.ride.domain.entity.IResource
import com.atlas.ride.domain.entity.IService

@Entity(tableName = "Services")
data class Service(
    val id: Int,

    override val name: String,
    override val description: String,

    @Ignore
    override val parent: Thing,
    override val function: IService.Function,

    @Ignore
    private val resource: Resource
) : IResource by resource, IService {
    override val type: IPrimitive.Type
        get() = IPrimitive.Type.SERVICE
}

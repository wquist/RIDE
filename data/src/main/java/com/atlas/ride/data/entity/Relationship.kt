package com.atlas.ride.data.entity

import androidx.room.Entity
import androidx.room.Ignore

import com.atlas.ride.domain.entity.IPrimitive
import com.atlas.ride.domain.entity.IRelationship
import com.atlas.ride.domain.entity.IResource

@Entity(tableName = "Relationships")
data class Relationship(
    val id: Int,

    override val name: String,
    override val description: String,

    override val connection: IRelationship.Connection,

    @Ignore
    override val trigger: Service,
    @Ignore
    override val action: Service,

    @Ignore
    private val resource: Resource
) : IResource by resource, IRelationship {
    override val type: IPrimitive.Type
        get() = IPrimitive.Type.RELATIONSHIP
}

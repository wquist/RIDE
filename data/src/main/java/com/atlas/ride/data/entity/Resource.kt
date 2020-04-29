package com.atlas.ride.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.atlas.ride.domain.entity.IPrimitive
import com.atlas.ride.domain.entity.IResource

/**
 * A representation of a resource object. This is the same as the inner fields type, as a resource
 * contains no references to other object types.
 */
data class Resource(
    @Embedded
    private val fields: Fields
) : IResource by fields {
    /**
     * A representation of the concrete fields (which is all fields) of a resource object.
     */
    @Entity(tableName = "Resources")
    class Fields(
        @PrimaryKey(autoGenerate = true)
        val resourceId: Int,

        override val type: IPrimitive.Type,

        override val icon: String,
        override val color: Int
    ) : IResource
}

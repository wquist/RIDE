package com.atlas.ride.data.entity

import androidx.room.*

import com.atlas.ride.domain.entity.IPrimitive
import com.atlas.ride.domain.entity.IResource
import com.atlas.ride.domain.entity.IService

/**
 * A complete representation of a service object. In addition to supported access of its parent
 * information, this type also includes the fields from its associated resource object (its
 * inherited type).
 */
data class Service(
    @Embedded
    val fields: Fields,

    @Relation(parentColumn = "serviceId", entityColumn = "resourceId")
    val resource: Resource.Fields,

    @Relation(parentColumn = "parentThingId", entityColumn = "thingId")
    private val parent: Thing.Fields? = null
) : IResource by resource, IService by fields {
    /**
     * A partial representation of a service object, containing only the data fields specified
     * in exactly [IService]. Note that this type must be accessed carefully, as although it
     * represents a service, it contains no [IResource] fields on its own.
     */
    @Entity(tableName = "Services", foreignKeys = [
        ForeignKey(
            entity = Resource.Fields::class,
            parentColumns = ["resourceId"],
            childColumns = ["serviceId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
    abstract class Fields(
        @PrimaryKey(autoGenerate = false)
        val serviceId: Int,

        override val name: String,
        override val description: String,

        override val function: IService.Function,

        val parentThingId: Int
    ) : IService

    override val type: IPrimitive.Type
        get() = IPrimitive.Type.SERVICE

    override val icon: String
        get() = resource.icon
    override val color: Int
        get() = resource.color

    override fun getParent() = parent ?: throw UnsupportedOperationException(
        "This instance of IService does not contain a valid reference to its parent data."
    )
}

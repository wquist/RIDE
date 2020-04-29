package com.atlas.ride.data.entity

import androidx.room.*

import com.atlas.ride.domain.entity.IPrimitive
import com.atlas.ride.domain.entity.IRelationship
import com.atlas.ride.domain.entity.IResource

/**
 * A complete representation of a relationship object. This type includes the fields from its
 * associated resource object (its inherited type).
 */
data class Relationship(
    @Embedded
    val fields: Fields,

    @Relation(parentColumn = "relationshipId", entityColumn = "resourceId")
    val resource: Resource.Fields,

    @Relation(parentColumn = "triggerServiceId", entityColumn = "serviceId")
    private val trigger: Service.Fields? = null,
    @Relation(parentColumn = "actionServiceId", entityColumn = "serviceId")
    private val action: Service.Fields? = null
) : IResource by resource, IRelationship by fields {
    /**
     * A partial representation of a relationship object, containing only the data fields specified
     * in exactly [IRelationship]. Note that this type must be accessed carefully, as although it
     * represents a relationship, it contains no [IResource] fields on its own.
     */
    @Entity(tableName = "Relationships", foreignKeys = [
        ForeignKey(
            entity = Resource.Fields::class,
            parentColumns = ["resourceId"],
            childColumns = ["relationshipId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
    class Fields(
        // This field will always be set to the parent resourceId.
        @PrimaryKey(autoGenerate = true)
        val relationshipId: Int,

        override val name: String,
        override val description: String,

        override val connection: IRelationship.Connection,

        val triggerServiceId: Int,
        val actionServiceId: Int
    ) : IRelationship {
        override val type: IPrimitive.Type
            get() = IPrimitive.Type.RELATIONSHIP

        override val icon: String
            get() = throw UnsupportedOperationException(
                "This instance of IRelationship does not contain a valid icon."
            )
        override val color: Int
            get() = throw UnsupportedOperationException(
                "This instance of IRelationship does not contain a valid color."
            )

        override fun getTrigger() = throw UnsupportedOperationException(
            "This instance of IRelationship does not contain valid trigger service data."
        )
        override fun getAction() = throw UnsupportedOperationException(
            "This instance of IRelationship does not contain valid action service data."
        )
    }

    override val type: IPrimitive.Type
        get() = fields.type

    override val icon: String
        get() = resource.icon
    override val color: Int
        get() = resource.color

    override fun getTrigger() = trigger ?: fields.getTrigger()
    override fun getAction() = action ?: fields.getAction()
}

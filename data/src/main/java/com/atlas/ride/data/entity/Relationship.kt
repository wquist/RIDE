package com.atlas.ride.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

import com.atlas.ride.domain.entity.IRelationship

data class Relationship(
    @Embedded
    val fields: Fields,

    @Relation(parentColumn = "triggerServiceId", entityColumn = "serviceId")
    private val trigger: Service.Fields? = null,
    @Relation(parentColumn = "actionServiceId", entityColumn = "serviceId")
    private val action: Service.Fields? = null
) : IRelationship by fields {
    @Entity(tableName = "Relationships")
    abstract class Fields(
        @PrimaryKey(autoGenerate = true)
        val relationshipId: Int,

        override val name: String,
        override val description: String,

        override val connection: IRelationship.Connection,

        val triggerServiceId: Int,
        val actionServiceId: Int
    ) : IRelationship

    override fun getTrigger() = trigger ?: throw UnsupportedOperationException(
        "This instance of IRelationship does not contain valid trigger service data."
    )
    override fun getAction() = action ?: throw UnsupportedOperationException(
        "This instance of IRelationship does not contain valid action service data."
    )
}

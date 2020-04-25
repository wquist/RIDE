package com.atlas.ride.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

import com.atlas.ride.domain.entity.IThing

/**
 * A complete representation of a thing object. This composite type queries the internal thing
 * fields, as well as its list of child services. Therefore, accessing the service list will not
 * result in an exception.
 */
data class Thing(
    @Embedded
    val fields: Fields,

    @Relation(parentColumn = "thingId", entityColumn = "parentThingId")
    private val services: List<Service.Fields>? = null
) : IThing by fields {
    /**
     * A partial representation of a thing object, containing only the data fields that can be
     * represented within a single database table.
     */
    @Entity(tableName = "Things")
    abstract class Fields(
        @PrimaryKey(autoGenerate = true)
        val thingId: Int,

        override val name: String,
        override val description: String,

        override val icon: String,
        override val color: Int,

        override val status: IThing.Status
    ) : IThing

    override fun getServices() = services ?: throw UnsupportedOperationException(
        "This instance of IThing does not contain a valid set of service data."
    )
}

package com.atlas.ride.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

import com.atlas.ride.domain.entity.IRecipe

data class Recipe(
    @Embedded
    val fields: Fields
) : IRecipe by fields {
    @Entity(tableName = "Recipes")
    abstract class Fields(
        @PrimaryKey(autoGenerate = true)
        val id: Int,

        override val name: String,
        override val description: String
    ) : IRecipe

    override fun getResources() = throw UnsupportedOperationException(
        "This instance of IRecipe does not contain a valid set of resource data."
    )
}

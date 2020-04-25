package com.atlas.ride.data.entity

import androidx.room.*

import com.atlas.ride.domain.entity.IRecipe

data class Recipe(
    @Embedded
    val fields: Fields,

    @Relation(
        parentColumn = "recipeId",
        entityColumn = "resourceId",
        associateBy = Junction(RecipeResourceRef::class)
    )
    private val resources: List<Resource.Fields>? = null
) : IRecipe by fields {
    @Entity(tableName = "Recipes")
    abstract class Fields(
        @PrimaryKey(autoGenerate = true)
        val recipeId: Int,

        override val name: String,
        override val description: String
    ) : IRecipe

    override fun getResources() = resources ?: throw UnsupportedOperationException(
        "This instance of IRecipe does not contain a valid set of resource data."
    )
}

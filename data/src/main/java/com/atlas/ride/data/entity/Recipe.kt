package com.atlas.ride.data.entity

import androidx.room.*

import com.atlas.ride.domain.entity.IPrimitive
import com.atlas.ride.domain.entity.IRecipe

/**
 * A complete representation of a recipe object. In addition to the internal fields, this also
 * contains references to the list of owned resource objects.
 */
data class Recipe(
    @Embedded
    private val fields: Fields,

    @Relation(
        parentColumn = "recipeId",
        entityColumn = "resourceId",
        associateBy = Junction(ResourceRef::class)
    )
    private val resources: List<Resource.Fields>? = null
) : IRecipe by fields {
    /**
     * A partial representation of a recipe object. This contains only fields that are stored
     * within the cells of the individual 'Recipes' database table.
     */
    @Entity(tableName = "Recipes")
    class Fields(
        @PrimaryKey(autoGenerate = true)
        val recipeId: Int,

        override val name: String,
        override val description: String
    ) : IRecipe {
        override val type: IPrimitive.Type
            get() = IPrimitive.Type.RECIPE

        override fun getResources() = throw UnsupportedOperationException(
            "This instance of IRecipe does not contain a valid set of resource data."
        )
    }

    /**
     * An intermediate data type to represent the many-to-many relationship between recipes and
     * their child resource objects.
     */
    @Entity(
        tableName = "RecipeResources",
        primaryKeys = ["recipeId", "resourceId"],
        indices = [
            Index("recipeId"),
            Index("resourceId")
        ],
        foreignKeys = [
            ForeignKey(
                entity = Fields::class,
                parentColumns = ["recipeId"],
                childColumns = ["recipeId"],
                onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                entity = Resource.Fields::class,
                parentColumns = ["resourceId"],
                childColumns = ["resourceId"],
                onDelete = ForeignKey.CASCADE
            )
        ]
    )
    data class ResourceRef(
        val recipeId: Int,
        val resourceId: Int
    )

    override fun getResources() = resources ?: fields.getResources()
}

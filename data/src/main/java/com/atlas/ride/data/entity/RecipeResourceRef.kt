package com.atlas.ride.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "RecipeResources",
    primaryKeys = ["recipeId", "resourceId"],
    indices = [
        Index("recipeId"),
        Index("resourceId")
    ],
    foreignKeys = [
        ForeignKey(
            entity = Recipe.Fields::class,
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
data class RecipeResourceRef(
    val recipeId: Int,
    val resourceId: Int
)

package com.atlas.ride.data.entity

import androidx.room.Entity

@Entity(tableName = "RecipeResources", primaryKeys = ["recipeId", "resourceId"])
data class RecipeResourceRef(
    val recipeId: Int,
    val resourceId: Int
)

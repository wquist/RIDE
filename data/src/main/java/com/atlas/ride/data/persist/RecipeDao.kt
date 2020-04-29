package com.atlas.ride.data.persist

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

import kotlinx.coroutines.flow.Flow

import com.atlas.ride.data.entity.Recipe

@Dao
abstract class RecipeDao : BaseDao<Recipe.Fields>() {
    @Query("SELECT * FROM Recipes ORDER BY name")
    abstract fun get(): Flow<List<Recipe.Fields>>

    @Transaction @Query("SELECT * FROM Recipes WHERE recipeId = :id")
    abstract fun get(id: Int): Flow<Recipe>
}

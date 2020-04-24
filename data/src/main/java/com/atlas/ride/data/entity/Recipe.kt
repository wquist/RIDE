package com.atlas.ride.data.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

import com.atlas.ride.domain.entity.IPrimitive
import com.atlas.ride.domain.entity.IRecipe

@Entity(tableName = "Recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    override val name: String,
    override val description: String,

    @Ignore
    override val sequence: List<Resource>
) : IRecipe {
    override val type: IPrimitive.Type
        get() = IPrimitive.Type.RECIPE
}

package com.atlas.ride.data.persist

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.atlas.ride.data.entity.*
import com.atlas.ride.data.util.EntityConverters

@TypeConverters(EntityConverters::class) @Database(version = 1, entities = [
    Thing.Fields::class,
    Recipe.Fields::class,
    Recipe.ResourceRef::class,
    Resource.Fields::class,
    Relationship.Fields::class,
    Service.Fields::class
])
abstract class PrimitivesDatabase : RoomDatabase() {
    abstract fun thingDao(): ThingDao
    abstract fun recipeDao(): RecipeDao
    abstract fun resourceDao(): ResourceDao
    abstract fun relationshipDao(): RelationshipDao
    abstract fun serviceDao(): ServiceDao
}

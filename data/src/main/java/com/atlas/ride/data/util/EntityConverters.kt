package com.atlas.ride.data.util

import androidx.room.TypeConverter

import com.atlas.ride.domain.entity.IPrimitive
import com.atlas.ride.domain.entity.IRelationship
import com.atlas.ride.domain.entity.IService
import com.atlas.ride.domain.entity.IThing

class EntityConverters {
    @TypeConverter
    fun fromThingStatus(status: IThing.Status?): Int? = status?.ordinal

    @TypeConverter
    fun toThingStatus(int: Int?): IThing.Status? = int?.let {
        enumValues<IThing.Status>()[it]
    }

    @TypeConverter
    fun fromPrimitiveType(type: IPrimitive.Type?): Int? = type?.ordinal

    @TypeConverter
    fun toPrimitiveType(int: Int?): IPrimitive.Type? = int?.let {
        enumValues<IPrimitive.Type>()[it]
    }

    @TypeConverter
    fun fromRelationshipConnection(type: IRelationship.Connection?): Int? = type?.ordinal

    @TypeConverter
    fun toRelationshipConnection(int: Int?): IRelationship.Connection? = int?.let {
        enumValues<IRelationship.Connection>()[it]
    }

    @TypeConverter
    fun fromServiceFunction(type: IService.Function?): Int? = type?.ordinal

    @TypeConverter
    fun toServiceFunction(int: Int?): IService.Function? = int?.let {
        enumValues<IService.Function>()[it]
    }
}

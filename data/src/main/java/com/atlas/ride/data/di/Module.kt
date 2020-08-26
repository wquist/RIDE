package com.atlas.ride.data.di

import androidx.room.Room

import org.koin.dsl.module

import com.atlas.ride.data.persist.PrimitivesDatabase
import com.atlas.ride.data.repo.ThingsRepository
import com.atlas.ride.domain.repo.IThingsRepository

object Module {
    val declarations = module {
        single<IThingsRepository> { ThingsRepository(get()) }

        single {
            Room.databaseBuilder(get(), PrimitivesDatabase::class.java, "ride.db")
                .createFromAsset("database/test.db")
                .build()
        }
    }
}

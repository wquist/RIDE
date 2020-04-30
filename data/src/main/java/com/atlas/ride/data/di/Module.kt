package com.atlas.ride.data.di

import android.content.Context
import androidx.room.Room

import org.koin.dsl.module

import com.atlas.ride.data.R
import com.atlas.ride.data.persist.PrimitivesDatabase
import com.atlas.ride.data.repo.ThingsRepository
import com.atlas.ride.domain.repo.IThingsRepository

object Module {
    val declarations = module {
        single<IThingsRepository> { ThingsRepository(get()) }

        single {
            val name = get<Context>().getString(R.string.database_name)
            Room.databaseBuilder(get(), PrimitivesDatabase::class.java, name).build()
        }
    }
}

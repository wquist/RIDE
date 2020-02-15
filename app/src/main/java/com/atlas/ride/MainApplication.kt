package com.atlas.ride

import android.app.Application

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

import com.atlas.ride.data.di.Module.declarations as data
import com.atlas.ride.presentation.di.Module.declarations as presentation

@Suppress("unused")
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)

            modules(listOf(data, presentation))
        }
    }
}

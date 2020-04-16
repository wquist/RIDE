package com.atlas.ride.presentation.di

import org.koin.dsl.module

import com.atlas.ride.presentation.util.IFontIcons
import com.atlas.ride.presentation.util.MaterialIcons

object Module {
    val declarations = module {
        single<IFontIcons> { MaterialIcons(get()) }
    }
}

package com.atlas.ride.presentation.di

import org.koin.dsl.module

import com.atlas.ride.presentation.util.IIconFont
import com.atlas.ride.presentation.util.IPalette
import com.atlas.ride.presentation.util.MaterialColors
import com.atlas.ride.presentation.util.MaterialIcons

object Module {
    val declarations = module {
        single<IIconFont> { MaterialIcons(get()) }
        single<IPalette> { MaterialColors(get()) }
    }
}

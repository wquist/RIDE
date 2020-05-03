package com.atlas.ride.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import com.atlas.ride.presentation.feature.list.ThingsViewModel
import com.atlas.ride.presentation.graphics.IIconFont
import com.atlas.ride.presentation.graphics.IPalette
import com.atlas.ride.presentation.util.MaterialColors
import com.atlas.ride.presentation.util.MaterialIcons

object Module {
    val declarations = module {
        single<IIconFont> { MaterialIcons(get()) }
        single<IPalette> { MaterialColors(get()) }

        viewModel { ThingsViewModel(get()) }
    }
}

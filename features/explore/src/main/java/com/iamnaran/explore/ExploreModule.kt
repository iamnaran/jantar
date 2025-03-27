package com.iamnaran.explore

import com.iamnaran.explore.presentation.ExploreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val exploreModule: Module = module {

    viewModel { ExploreViewModel(get()) }

}
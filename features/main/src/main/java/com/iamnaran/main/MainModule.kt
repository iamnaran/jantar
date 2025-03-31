package com.iamnaran.main


import com.iamnaran.main.presentation.MainViewModel
import com.iamnaran.main.presentation.SharedViewModel
import com.iamnaran.navigation.di.featureModules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val allFeatureModules = featureModules

val mainModules = module {
    viewModel { MainViewModel(get()) }
    viewModel { SharedViewModel(get()) }
}

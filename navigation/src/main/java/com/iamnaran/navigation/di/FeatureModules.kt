package com.iamnaran.navigation.di

import com.iamnaran.auth.authModule
import com.iamnaran.explore.exploreModule
import com.iamnaran.home.homeModule
import com.iamnaran.info.infoModule
import org.koin.dsl.module

val featureModules = module {
    includes(infoModule)
    includes(authModule)
    includes(exploreModule)
    includes(homeModule)
}
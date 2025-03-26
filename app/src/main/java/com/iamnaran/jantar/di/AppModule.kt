package com.iamnaran.jantar.di

import com.iamnaran.common.commonModule
import com.iamnaran.main.mainModules
import com.iamnaran.network.networkModule

val appModules = listOf(
    commonModule,
    networkModule,
    mainModules,
)
package com.iamnaran.jantar.di

import com.iamnaran.di.dispatcherModule
import com.iamnaran.info.infoModule
import com.iamnaran.network.httpModule

val appModules = listOf(
    dispatcherModule,
    httpModule,
    infoModule,
)
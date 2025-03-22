package com.iamnaran.common

import com.iamnaran.common.utils.ImageUtils
import com.iamnaran.di.DispatcherType
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    single { ImageUtils(androidContext(),get(named(DispatcherType.IO.name))) }
}
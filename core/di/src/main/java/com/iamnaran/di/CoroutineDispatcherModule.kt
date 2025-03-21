package com.iamnaran.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutineDispatcherModule: Module = module {
    single<CoroutineDispatcher>(named("IO")) { Dispatchers.IO }
    single<CoroutineDispatcher>(named("Default")) { Dispatchers.Default }
    single<CoroutineDispatcher>(named("Main")) { Dispatchers.Main }
}
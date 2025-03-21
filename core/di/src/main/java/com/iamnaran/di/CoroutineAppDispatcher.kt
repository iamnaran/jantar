package com.iamnaran.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class DispatcherType {
    IO,
    DEFAULT,
    MAIN
}

val coroutineDispatcherModule: Module = module {
    single<CoroutineDispatcher>(named(DispatcherType.IO.name)) { Dispatchers.IO }
    single<CoroutineDispatcher>(named(DispatcherType.DEFAULT.name)) { Dispatchers.Default }
    single<CoroutineDispatcher>(named(DispatcherType.MAIN.name)) { Dispatchers.Main }
}
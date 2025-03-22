package com.iamnaran.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class DispatcherType {
    IO,
    DEFAULT,
    MAIN
}

val dispatcherModule: Module = module {
    single(named(DispatcherType.IO.name)) { Dispatchers.IO }
    single(named(DispatcherType.DEFAULT.name)) { Dispatchers.Default }
    single(named(DispatcherType.MAIN.name)) { Dispatchers.Main }
}
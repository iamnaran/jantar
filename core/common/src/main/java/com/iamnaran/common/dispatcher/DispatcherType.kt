package com.iamnaran.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

enum class DispatcherType {
    IO,
    DEFAULT,
    MAIN
}
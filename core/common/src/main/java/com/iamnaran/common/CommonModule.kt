package com.iamnaran.common

import android.content.Context
import com.iamnaran.common.dispatcher.DispatcherType
import com.iamnaran.common.utils.ImageUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    single(named(DispatcherType.IO.name)) { provideDispatcherIO() }
    single(named(DispatcherType.DEFAULT.name)) { provideDispatcherDefault() }
    single { provideImageUtils(get(), get(named(DispatcherType.IO.name))) }
}

fun provideImageUtils(context: Context, dispatcher: CoroutineDispatcher): ImageUtils {
    return ImageUtils(context, dispatcher)
}

fun provideDispatcherIO(): CoroutineDispatcher {
    return Dispatchers.Default
}

fun provideDispatcherDefault(): CoroutineDispatcher {
    return Dispatchers.IO
}
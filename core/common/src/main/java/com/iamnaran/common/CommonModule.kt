package com.iamnaran.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.iamnaran.common.data.PrefDataStoreHelper
import com.iamnaran.common.data.PrefDataStoreHelperImpl
import com.iamnaran.common.data.PrefKeys
import com.iamnaran.common.dispatcher.DispatcherType
import com.iamnaran.common.utils.ImageUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    single(named(DispatcherType.IO.name)) { provideDispatcherIO() }
    single(named(DispatcherType.DEFAULT.name)) { provideDispatcherDefault() }
    single { provideImageUtils(get(), get(named(DispatcherType.IO.name))) }

    single<DataStore<Preferences>> { provideDataStore(androidContext()) }
    single<PrefDataStoreHelper> { PrefDataStoreHelperImpl(get()) }

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


fun provideDataStore(context: Context) : DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        produceFile = { context.preferencesDataStoreFile(PrefKeys.PREF_FILE_NAME) }
    )
}
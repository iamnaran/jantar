package com.iamnaran.database

import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    single {
        provideDatabase(androidContext())
    }
    single { get<AppDatabase>().productDao() }
}


fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "x_jantar_x"
    ).fallbackToDestructiveMigration().build()
}
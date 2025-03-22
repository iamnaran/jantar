package com.iamnaran.database

import android.app.Application
import androidx.room.Room
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            "x_jantar_x"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().productDao() }
}
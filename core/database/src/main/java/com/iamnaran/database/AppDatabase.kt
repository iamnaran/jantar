package com.iamnaran.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iamnaran.database.dao.ProductDao
import com.iamnaran.database.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 7, exportSchema = false)
@TypeConverters(AppTypeConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao


}
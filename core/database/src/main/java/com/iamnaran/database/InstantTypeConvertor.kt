package com.iamnaran.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class InstantTypeConvertor {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Json.encodeToString(list)
    }

}
package com.iamnaran.database.dao

import androidx.room.Dao
import com.iamnaran.database.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<ProductEntity>>

}
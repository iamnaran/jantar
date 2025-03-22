package com.iamnaran.database.entity

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity("product")
@Immutable
data class ProductEntity(

    @PrimaryKey
    @ColumnInfo("id")
    @SerialName("id")
    val id: Long,

    @ColumnInfo("title")
    @SerialName("title")
    val title: String,

    @ColumnInfo("description")
    @SerialName("description")
    val description: String,

    @ColumnInfo("category")
    @SerialName("category")
    val category: String,

    @ColumnInfo("price")
    @SerialName("price")
    val price: Float,

    @ColumnInfo("rating")
    @SerialName("rating")
    val rating: String,

    @ColumnInfo("stock")
    @SerialName("stock")
    val stock: Int,

    @ColumnInfo("thumbnail")
    @SerialName("thumbnail")
    val thumbnail: String,
)
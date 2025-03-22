package com.iamnaran.database.entity

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity("user")
@Immutable
data class UserEntity(
    @ColumnInfo(name = "id")
    @SerialName("id")
    @PrimaryKey val id: Long,

    @ColumnInfo(name = "fullName")
    @SerialName("fullName")
    val fullName: String,

    @ColumnInfo(name = "email")
    @SerialName("email")
    val email: String,

    @ColumnInfo(name = "gender")
    @SerialName("gender")
    val gender: String,

    @ColumnInfo(name = "image")
    @SerialName("image")
    val image: String,

    @ColumnInfo(name = "username")
    @SerialName("username")
    val username: String,

    )
package com.iamnaran.auth.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("id") val id: Int,
    @SerialName("username") val username: String,
    @SerialName("email") val email: String,
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("gender") val gender: String,
    @SerialName("image") val image: String,
    @SerialName("accessToken") val token: String,
    @SerialName("refreshToken") val refreshToken: String,
)


//fun UserResponse.toUserEntity() = UserEntity(
//    id = id.toLong(),
//    fullName = "$firstName $lastName",
//    email = email,
//    gender = gender,
//    image = image,
//    username = username
//);
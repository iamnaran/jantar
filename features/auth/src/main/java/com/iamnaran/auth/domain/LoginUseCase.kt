package com.iamnaran.auth.domain

import com.iamnaran.auth.data.AuthRepository
import com.iamnaran.auth.data.LoginRequest
import com.iamnaran.auth.data.UserResponse
import com.iamnaran.network.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun execute(username: String, password: String): Flow<ApiResponse<UserResponse>>
}

class LoginUseCaseImpl(private val repository: AuthRepository) : LoginUseCase {
    override suspend fun execute(
        username: String,
        password: String
    ): Flow<ApiResponse<UserResponse>> {

        return repository.requestLogin(loginRequest = LoginRequest(username, password))
    }
}
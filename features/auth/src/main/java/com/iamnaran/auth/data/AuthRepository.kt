package com.iamnaran.auth.data

import com.iamnaran.auth.domain.AuthApiService
import com.iamnaran.network.utils.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    suspend fun requestLogin(loginRequest: LoginRequest): Flow<ApiResponse<UserResponse>>
}

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override suspend fun requestLogin(loginRequest: LoginRequest): Flow<ApiResponse<UserResponse>> {
        return authApiService.requestLogin(loginRequest)
    }
}
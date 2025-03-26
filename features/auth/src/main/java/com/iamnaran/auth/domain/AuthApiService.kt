package com.iamnaran.auth.domain

import com.iamnaran.auth.data.LoginRequest
import com.iamnaran.auth.data.RegisterRequest
import com.iamnaran.auth.data.UserResponse
import com.iamnaran.network.ApiEndPoints
import com.iamnaran.network.utils.ApiResponse
import com.iamnaran.network.utils.safeRequestWithFlow
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import io.ktor.http.Parameters
import io.ktor.utils.io.InternalAPI

interface AuthApiService {
    suspend fun requestLogin(request: LoginRequest): Flow<ApiResponse<UserResponse>>
    suspend fun requestRegister(request: RegisterRequest): Flow<ApiResponse<UserResponse>>
}

class AuthApiServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : AuthApiService {

    @OptIn(InternalAPI::class)
    override suspend fun requestLogin(
        request: LoginRequest
    ): Flow<ApiResponse<UserResponse>> = httpClient.safeRequestWithFlow<UserResponse> {
        method = HttpMethod.Post
        url(ApiEndPoints.LOGIN)
        body = FormDataContent(Parameters.build {
            append("username", request.username)
            append("password", request.password)
        })

    }.flowOn(dispatcher)

    override suspend fun requestRegister(
        request: RegisterRequest
    ): Flow<ApiResponse<UserResponse>> = httpClient.safeRequestWithFlow<UserResponse> {
        method = HttpMethod.Post
        url(ApiEndPoints.REGISTER)
        setBody(request)
    }.flowOn(dispatcher)


}
package com.iamnaran.home.data

import com.iamnaran.network.ApiEndPoints
import com.iamnaran.network.utils.ApiResponse
import com.iamnaran.network.utils.safeRequestWithFlow
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface ProductApiService {
    suspend fun getProducts(): Flow<ApiResponse<ProductResponse>>
}

class ProductApiServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : ProductApiService {


    override suspend fun getProducts(): Flow<ApiResponse<ProductResponse>> =
        httpClient.safeRequestWithFlow<ProductResponse> {
            method = HttpMethod.Get
            url(ApiEndPoints.PRODUCTS)
        }.flowOn(dispatcher)

}
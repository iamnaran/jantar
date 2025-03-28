package com.iamnaran.home.data
import com.iamnaran.network.utils.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface ProductRepository {
    suspend fun getProducts(): Flow<ApiResponse<ProductResponse>>
}

class ProductRepositoryImpl(
    private val apiService: ProductApiService,
    private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {

    override suspend fun getProducts(): Flow<ApiResponse<ProductResponse>> {
        return apiService.getProducts().flowOn(ioDispatcher)
    }
}
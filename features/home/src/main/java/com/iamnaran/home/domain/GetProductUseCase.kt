package com.iamnaran.home.domain

import com.iamnaran.home.data.Product
import com.iamnaran.home.data.ProductRepository
import com.iamnaran.home.data.ProductResponse
import com.iamnaran.network.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface GetProductUseCase {
    suspend fun execute(): Flow<ApiResponse<ProductResponse>>
}

public class GetProductUseCaseImpl(private val repository: ProductRepository) : GetProductUseCase {
    override suspend fun execute(): Flow<ApiResponse<ProductResponse>> {

        return repository.getProducts()
    }
}
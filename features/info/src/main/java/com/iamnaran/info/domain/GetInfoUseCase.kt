package com.iamnaran.info.domain

import com.iamnaran.info.data.Info
import com.iamnaran.info.data.InfoRepository
import com.iamnaran.network.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface GetInfoUseCase {
    suspend fun execute(base64Image: String): Flow<ApiResponse<Info>>
}

public class GetInfoUseCaseImpl(private val repository: InfoRepository) : GetInfoUseCase {
    override suspend fun execute(base64Image: String): Flow<ApiResponse<Info>> {

        return repository.getInfo(base64Image)
    }
}
package com.iamnaran.info.data

import com.iamnaran.network.utils.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface InfoRepository {
    suspend fun getInfo(imageBase64: String): Flow<ApiResponse<Info>>
}

class InfoRepositoryImpl(
    private val apiService: InfoApiService,
    private val ioDispatcher: CoroutineDispatcher
) : InfoRepository {

    override suspend fun getInfo(imageBase64: String): Flow<ApiResponse<Info>> {
        return apiService.getInfo(imageBase64).flowOn(ioDispatcher)
    }
}

//    suspend fun getInfo() = networkBoundResource(
//        query = {
//
//        },
//        fetch = {
//
//        },
//        saveFetchResult = {
//
//        }
//    )

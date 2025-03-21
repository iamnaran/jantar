package com.iamnaran.info.data

interface InfoRepository {
    suspend fun getInfo(): InfoResponse
}

class InfoRepositoryImpl(private val apiService: InfoApiService) : InfoRepository {
    override suspend fun getInfo(): InfoResponse {
        return apiService.getInfo()
    }
}
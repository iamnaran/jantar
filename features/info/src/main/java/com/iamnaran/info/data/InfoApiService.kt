package com.iamnaran.info.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface InfoApiService {
    suspend fun getInfo(): InfoResponse
}

class InfoApiServiceImpl(private val httpClient: HttpClient) : InfoApiService {
    override suspend fun getInfo(): InfoResponse {
        return httpClient.get("https://api.example.com/info").body()
    }
}
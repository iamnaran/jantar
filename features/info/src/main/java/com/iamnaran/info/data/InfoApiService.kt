package com.iamnaran.info.data

import com.iamnaran.network.ApiEndPoints
import com.iamnaran.network.ApiResponse
import com.iamnaran.network.safeRequestWithFlow
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.flow.Flow

interface InfoApiService {
    suspend fun getInfo(imageBase64: String): Flow<ApiResponse<Info>>
}

class InfoApiServiceImpl(private val httpClient: HttpClient) : InfoApiService {

    @OptIn(InternalAPI::class)
    override suspend fun getInfo(imageBase64: String): Flow<ApiResponse<Info>> =
        httpClient.safeRequestWithFlow {
            method = HttpMethod.Post
            url(ApiEndPoints.SCAN_MEDICINE)
            body = FormDataContent(Parameters.build {
                append("imageBase64", imageBase64)
            })
        }
}
package com.iamnaran.info.data

import com.iamnaran.network.ApiEndPoints
import com.iamnaran.network.utils.ApiResponse
import com.iamnaran.network.utils.safeRequestWithFlow
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface InfoApiService {
    suspend fun getInfo(imageBase64: String): Flow<ApiResponse<Info>>
}

class InfoApiServiceImpl(
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : InfoApiService {


    override suspend fun getInfo(imageBase64: String): Flow<ApiResponse<Info>> =
            httpClient.safeRequestWithFlow<Info> {
                val infoRequest = InfoRequest(image = imageBase64)
                method = HttpMethod.Post
                url(ApiEndPoints.SCAN_MEDICINE)
                setBody(infoRequest)
            }.flowOn(dispatcher)


//    body = FormDataContent(Parameters.build {
//        append("image", imageBase64)
//    })
}
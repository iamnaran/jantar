package com.iamnaran.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkModule {


//    val httpModule = module {
//        single<HttpClient> { provideKtorClient() }
//    }
//
//    val authServices = module {
//        single { AuthApiService(get()) }
//    }


    private fun provideKtorClient(): HttpClient {
        return HttpClient {
            expectSuccess = true

            install(Logging) {
                level = LogLevel.ALL
            }
            install(Logging) {
                logger = object: io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        // AppLog.showLog("HTTP status:" + "${response.status.value}")

                    }

                }
                level = LogLevel.HEADERS
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
                headers {
                    headersOf("Content-Type", "application/json")
                }
            }

            defaultRequest {
                url(ApiEndPoints.BASE_URL)
                contentType(ContentType.Application.Json)
            }

            install(HttpCache)

            install(HttpTimeout) {
                requestTimeoutMillis = 20000L
                connectTimeoutMillis = 20000L
                socketTimeoutMillis = 20000L
            }

            install(ResponseObserver) {
                onResponse { response ->
//                    AppLog.showLog("HTTP status:" + "${response.status.value}")
                }
            }
        }
    }


}
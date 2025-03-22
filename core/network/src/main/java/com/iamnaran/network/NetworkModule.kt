package com.iamnaran.network

import com.iamnaran.common.AppLog
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
import org.koin.dsl.module

val httpModule = module {
    single<HttpClient> { provideKtorClient() }
}

private fun provideKtorClient(): HttpClient {
    return HttpClient {
        expectSuccess = true

        install(Logging) {
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    AppLog.showLog("HTTP status:$message")
                }
            }
            level = LogLevel.BODY
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        defaultRequest {
            url(ApiEndPoints.BASE_URL)
            contentType(ContentType.Application.Json)
        }

        install(HttpCache)

        install(HttpTimeout) {
            requestTimeoutMillis = 100000L
            connectTimeoutMillis = 100000L
            socketTimeoutMillis = 100000L
        }

        install(ResponseObserver) {
            onResponse {
                AppLog.showLog("HTTP status:" + "${it.status.value}")
            }
        }
    }
}


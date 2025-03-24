package com.iamnaran.network.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(ApiResponse.Loading())

        try {
            saveFetchResult(fetch())
            query().map { ApiResponse.Success(it) }
        } catch (throwable: Throwable) {
            query().map { ApiResponse.Error.NetworkException(throwable.message.toString()) }
        }
    } else {
        query().map { ApiResponse.Success(it) }
    }

    emitAll(flow)
}


inline fun <ResultType> networkBoundResourceOnly(
    crossinline fetch: suspend () -> ResultType
) = flow {
    emit(ApiResponse.Loading())
    try {
        val result = fetch()
        emit(ApiResponse.Success(result))
    } catch (throwable: Throwable) {
        emit(ApiResponse.Error.NetworkException(throwable.message.toString()))
    }
}
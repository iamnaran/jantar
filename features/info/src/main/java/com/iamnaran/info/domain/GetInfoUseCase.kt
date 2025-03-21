package com.iamnaran.info.domain

import com.iamnaran.info.data.InfoRepository
import com.iamnaran.info.data.InfoResponse

interface GetInfoUseCase {
    suspend fun execute(): InfoResponse
}

public class GetInfoUseCaseImpl(private val repository: InfoRepository) : GetInfoUseCase {
    override suspend fun execute(): InfoResponse {
        return repository.getInfo()
    }
}
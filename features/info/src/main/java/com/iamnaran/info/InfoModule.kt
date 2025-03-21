package com.iamnaran.info

import com.iamnaran.info.data.InfoApiService
import com.iamnaran.info.data.InfoApiServiceImpl
import com.iamnaran.info.data.InfoRepository
import com.iamnaran.info.data.InfoRepositoryImpl
import com.iamnaran.info.domain.GetInfoUseCase
import com.iamnaran.info.domain.GetInfoUseCaseImpl
import com.iamnaran.info.presentation.InfoViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val infoModule: Module = module {
    single<InfoApiService> { InfoApiServiceImpl(get<HttpClient>()) }
    single<InfoRepository> { InfoRepositoryImpl(get()) }
    single<GetInfoUseCase> { GetInfoUseCaseImpl(get()) }
    factory { InfoViewModel() }
}
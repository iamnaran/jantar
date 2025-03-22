package com.iamnaran.info

import com.iamnaran.di.DispatcherType
import com.iamnaran.info.data.InfoApiService
import com.iamnaran.info.data.InfoApiServiceImpl
import com.iamnaran.info.data.InfoRepository
import com.iamnaran.info.data.InfoRepositoryImpl
import com.iamnaran.info.domain.GetInfoUseCase
import com.iamnaran.info.domain.GetInfoUseCaseImpl
import com.iamnaran.info.presentation.InfoViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val infoModule: Module = module {
    single<InfoApiService> {
        InfoApiServiceImpl(
            get(),
            dispatcher = get(named(DispatcherType.IO.name))
        )
    }
    single<InfoRepository> { InfoRepositoryImpl(get(), get(named(DispatcherType.IO.name))) }
    single<GetInfoUseCase> { GetInfoUseCaseImpl(get()) }
    viewModel { InfoViewModel(get(named(DispatcherType.IO.name)), get(), get()) }

}
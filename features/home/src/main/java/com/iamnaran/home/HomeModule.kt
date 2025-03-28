package com.iamnaran.home

import com.iamnaran.common.dispatcher.DispatcherType
import com.iamnaran.home.data.ProductApiService
import com.iamnaran.home.data.ProductApiServiceImpl
import com.iamnaran.home.data.ProductRepository
import com.iamnaran.home.data.ProductRepositoryImpl
import com.iamnaran.home.domain.GetProductUseCase
import com.iamnaran.home.domain.GetProductUseCaseImpl
import com.iamnaran.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeModule: Module = module {
    single<ProductApiService> {
        ProductApiServiceImpl(
            get(),
            dispatcher = get(named(DispatcherType.IO.name))
        )
    }
    single<ProductRepository> { ProductRepositoryImpl(get(), get(named(DispatcherType.IO.name))) }
    single<GetProductUseCase> { GetProductUseCaseImpl(get()) }
    viewModel { HomeViewModel(get()) }

}
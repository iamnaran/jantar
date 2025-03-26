package com.iamnaran.auth

import com.iamnaran.auth.data.AuthRepository
import com.iamnaran.auth.data.AuthRepositoryImpl
import com.iamnaran.auth.domain.AuthApiService
import com.iamnaran.auth.domain.AuthApiServiceImpl
import com.iamnaran.auth.domain.LoginUseCase
import com.iamnaran.auth.domain.LoginUseCaseImpl
import com.iamnaran.auth.presentation.login.LoginViewModel
import com.iamnaran.common.dispatcher.DispatcherType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authModule: Module = module {
    single<AuthApiService> {
        AuthApiServiceImpl(
            get(),
            dispatcher = get(named(DispatcherType.IO.name))
        )
    }
    single<AuthRepository> { AuthRepositoryImpl(get(), get(named(DispatcherType.IO.name))) }
    single<LoginUseCase> { LoginUseCaseImpl(get()) }
    viewModel { LoginViewModel(get()) }

}
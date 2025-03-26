package com.iamnaran.auth.presentation.login

import com.iamnaran.auth.data.UserResponse

sealed interface LoginState {
    data object Idle : LoginState
    data object Loading : LoginState
    data class Success(val data: UserResponse) : LoginState
    data class Error(val message: String) : LoginState
}

data class LoginInputFieldsState(
    val emailOrPhone: String = "emilys",
    val password: String = "emilyspass"
)

sealed interface LoginUiEvent {
    data object Idle : LoginUiEvent
    data object OnLogin : LoginUiEvent
    data object OnForgotPassword : LoginUiEvent
    data class OnEmailOrPhoneChanged(val email: String) : LoginUiEvent
    data class OnPasswordChanged(val password: String) : LoginUiEvent
}
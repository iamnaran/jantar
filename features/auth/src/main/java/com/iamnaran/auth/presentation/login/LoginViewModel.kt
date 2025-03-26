package com.iamnaran.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamnaran.auth.domain.LoginUseCase
import com.iamnaran.common.log.AppLog
import com.iamnaran.network.utils.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _loginInputFieldsState = MutableStateFlow(LoginInputFieldsState())
    val loginInputFieldsState: StateFlow<LoginInputFieldsState> = _loginInputFieldsState

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            LoginUiEvent.OnLogin -> {
                requestLogin()
            }

            is LoginUiEvent.OnEmailOrPhoneChanged -> {
                onEmailOrPhoneChanged(emailOrPhone = event.email)
            }

            LoginUiEvent.OnForgotPassword -> {

            }

            is LoginUiEvent.OnPasswordChanged -> {
                onPasswordChanged(password = event.password)
            }

            else -> {}
        }
    }

    private fun onEmailOrPhoneChanged(emailOrPhone: String) {
        _loginInputFieldsState.value = _loginInputFieldsState.value.copy(emailOrPhone = emailOrPhone)
    }

    private fun onPasswordChanged(password: String) {
        _loginInputFieldsState.value = _loginInputFieldsState.value.copy(password = password)
    }


    private fun requestLogin() {

        if (_loginInputFieldsState.value.emailOrPhone.isBlank() || _loginInputFieldsState.value.password.isBlank()) {
            _loginState.value = LoginState.Error("Email/Phone and password cannot be empty")
            return
        }

        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            loginUseCase.execute(_loginInputFieldsState.value.emailOrPhone, _loginInputFieldsState.value.password)
                .catch { e ->
                    AppLog.showLog(e.message.toString())
                    _loginState.value = LoginState.Error(e.message ?: "Unknown error")
                }
                .collectLatest {
                    when (it) {

                        is ApiResponse.Error -> {
                            AppLog.showLog("Else error $it")

                        }

                        is ApiResponse.Loading -> {
                            _loginState.value = LoginState.Loading
                        }

                        is ApiResponse.Success -> {
                            _loginState.value = LoginState.Success(it.body)
                        }

                        else -> {
                            AppLog.showLog("Else error")
                            _loginState.value = LoginState.Error("Unknown error")
                        }
                    }

                }
        }

    }


}
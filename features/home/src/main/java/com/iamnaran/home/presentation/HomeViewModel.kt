package com.iamnaran.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamnaran.common.log.AppLog
import com.iamnaran.home.data.Product
import com.iamnaran.home.data.ProductResponse
import com.iamnaran.home.domain.GetProductUseCase
import com.iamnaran.network.utils.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUIState<ProductResponse>>(HomeUIState.Loading)
    val uiState: StateFlow<HomeUIState<ProductResponse>> = _uiState

    init {
        requestProducts()
    }

    private fun requestProducts() {
        viewModelScope.launch {
            _uiState.value = HomeUIState.Loading
            getProductUseCase.execute()
                .catch { e ->
                    AppLog.showLog(e.message.toString())
                    _uiState.value = HomeUIState.Error(e.message ?: "Unknown error")
                }
                .collectLatest { info ->
                    when (info) {

                        is ApiResponse.Loading -> _uiState.value = HomeUIState.Loading

                        is ApiResponse.Success -> {
                            _uiState.value = HomeUIState.Success(info.body)
                        }

                        else -> {
                            AppLog.showLog("Else error")
                            _uiState.value = HomeUIState.Error("Unknown error")
                        }
                    }

                }
        }
    }
}
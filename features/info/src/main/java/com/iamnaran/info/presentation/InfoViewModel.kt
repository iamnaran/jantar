package com.iamnaran.info.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamnaran.common.AppLog
import com.iamnaran.info.data.Info
import com.iamnaran.info.domain.GetInfoUseCase
import com.iamnaran.network.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class InfoViewModel(private val getInfoUseCase: GetInfoUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<InfoUIState<Info>>(InfoUIState.Loading)
    val uiState: StateFlow<InfoUIState<Info>> = _uiState


    fun fetchInfo(base64Image: String) {
        viewModelScope.launch {
            _uiState.value = InfoUIState.Loading
            getInfoUseCase.execute(base64Image)
                .catch { e ->
                    AppLog.showLog(e.message.toString())
                    _uiState.value = InfoUIState.Error(e.message ?: "Unknown error")
                }
                .collectLatest { info ->
                    when (info) {

                        is ApiResponse.Loading -> _uiState.value = InfoUIState.Loading

                        is ApiResponse.Success -> {
                            _uiState.value = InfoUIState.Success(info.body)
                        }

                        else -> {
                            AppLog.showLog("Else error")
                            _uiState.value = InfoUIState.Error("Unknown error")
                        }
                    }

                }
        }
    }


}
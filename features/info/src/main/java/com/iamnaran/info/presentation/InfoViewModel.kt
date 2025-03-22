package com.iamnaran.info.presentation

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamnaran.common.log.AppLog
import com.iamnaran.common.utils.ImageUtils
import com.iamnaran.info.data.Info
import com.iamnaran.info.domain.GetInfoUseCase
import com.iamnaran.network.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InfoViewModel(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val getInfoUseCase: GetInfoUseCase,
    private val imageUtils: ImageUtils
) : ViewModel() {

    private val _uiState = MutableStateFlow<InfoUIState<Info>>(InfoUIState.Loading)
    val uiState: StateFlow<InfoUIState<Info>> = _uiState

    fun processAndFetchInfo(capturedImageUri: String) {
        viewModelScope.launch {
            _uiState.value = InfoUIState.Loading

            val base64Image = withContext(coroutineDispatcher) {
                imageUtils.convertImageToBase64(Uri.parse(capturedImageUri))
            }

            if (base64Image.isNullOrEmpty()) {
                _uiState.value = InfoUIState.Error("Failed to process image")
                return@launch
            }

            fetchInfo(base64Image)
        }
    }


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
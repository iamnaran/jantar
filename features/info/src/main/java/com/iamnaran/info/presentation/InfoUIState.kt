package com.iamnaran.info.presentation

sealed interface InfoUIState<out T> {
    data object Loading : InfoUIState<Nothing>
    data class Success<T>(val data: T) : InfoUIState<T>
    data class Error(val message: String) : InfoUIState<Nothing>
    data object Empty : InfoUIState<Nothing>
}
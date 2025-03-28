package com.iamnaran.home.presentation

sealed interface HomeUIState<out T> {
    data object Loading : HomeUIState<Nothing>
    data class Success<T>(val data: T) : HomeUIState<T>
    data class Error(val message: String) : HomeUIState<Nothing>
    data object Empty : HomeUIState<Nothing>
}
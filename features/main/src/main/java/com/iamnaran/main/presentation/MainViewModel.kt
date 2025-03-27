package com.iamnaran.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamnaran.common.data.PrefDataStoreHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(private val prefDataStoreHelper: PrefDataStoreHelper) : ViewModel(){

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: MutableStateFlow<Boolean> get() = _isLoggedIn

    init {
        getLoggedInStatus()
    }

    private fun getLoggedInStatus(){

        viewModelScope.launch {
            prefDataStoreHelper.getLoggedInStatus().collectLatest {
                _isLoggedIn.value = it
            }

        }

    }


}
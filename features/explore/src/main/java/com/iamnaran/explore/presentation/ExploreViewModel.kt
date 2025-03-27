package com.iamnaran.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamnaran.common.data.PrefDataStoreHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ExploreViewModel(private val dataStorePrefDataStoreHelper: PrefDataStoreHelper) :
    ViewModel() {


    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut: MutableStateFlow<Boolean> get() = _isLoggedOut

    fun logout() {
        viewModelScope.launch {
            dataStorePrefDataStoreHelper.clearPreference()
            delay(100L)
            isLoggedOut.value = true
        }
    }

}
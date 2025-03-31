package com.iamnaran.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamnaran.common.data.PrefDataStoreHelper
import com.iamnaran.navigation.product.ProductEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SharedViewModel(private val prefDataStoreHelper: PrefDataStoreHelper) : ViewModel(){

    private val _showProductDetail = MutableStateFlow(ProductEvent())
    val showProductDetail: MutableStateFlow<ProductEvent> get() = _showProductDetail

    init {
        getProductEvent()
    }

    private fun getProductEvent(){


    }


}
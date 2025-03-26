package com.iamnaran.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iamnaran.home.data.Product
import com.iamnaran.home.data.getSampleProducts
import com.iamnaran.home.presentation.components.ProductLazyList

@Composable
fun HomeScreen(onNavigateBack: () -> Unit) {

    HomeContent()

}

@Composable
fun HomeContent() {




    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        ProductLazyList(getSampleProducts()) {

        }

    }



}

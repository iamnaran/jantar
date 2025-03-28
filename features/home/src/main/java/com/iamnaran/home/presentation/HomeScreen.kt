package com.iamnaran.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.iamnaran.home.data.ProductResponse
import com.iamnaran.home.presentation.components.ProductLazyList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(onNavigateBack: () -> Unit) {

    val viewModel: HomeViewModel = koinViewModel()
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()


    if (uiState.value is HomeUIState.Loading) {
        CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
    }

    if (uiState.value is HomeUIState.Success) {
        HomeContent(uiState.value as HomeUIState.Success<ProductResponse>)
    }

}

@Composable
fun HomeContent(productData: HomeUIState.Success<ProductResponse>) {


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        ProductLazyList(productData.data.productEntities) {

        }

    }


}

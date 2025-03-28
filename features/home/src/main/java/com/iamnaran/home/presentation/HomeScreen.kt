package com.iamnaran.home.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.iamnaran.home.data.Product
import com.iamnaran.home.data.ProductResponse
import com.iamnaran.home.presentation.components.ProductDetails
import com.iamnaran.home.presentation.components.ProductLazyList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(onNavigateBack: () -> Unit) {

    val viewModel: HomeViewModel = koinViewModel()
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()


    HomeContent(uiState.value)

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeContent(uiState: HomeUIState<ProductResponse>) {

    var selectedProduct: Product? by remember { mutableStateOf(null) }


    SharedTransitionLayout {
        AnimatedContent(
            targetState = selectedProduct,
            transitionSpec = {
                fadeIn(tween(600)) togetherWith fadeOut(tween(600)) using SizeTransform { _, _ -> spring() }
            },
            label = ""
        ) { product ->
            if (product == null) {
                if (uiState is HomeUIState.Success) {
                    ProductLazyList(
                        uiState.data.productEntities,
                        onProductClick = { selectedProduct = it },
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }
            } else {
                ProductDetails(
                    product = product,
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout
                ) {
                    selectedProduct = null
                }
            }
        }
    }


}

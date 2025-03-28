package com.iamnaran.home.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.iamnaran.home.data.Product

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductLazyList(
    allProductEntities: List<Product>,
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
    sharedTransitionScope: SharedTransitionScope? = null,
    onProductClick: (Product) -> Unit
) {

    LazyColumn {
        items(allProductEntities.size) { index ->
            val product = allProductEntities[index]
            ProductItem(
                product,
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
            ){
                onProductClick(product)
            }
        }

    }

}
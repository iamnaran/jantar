package com.iamnaran.home.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.iamnaran.home.data.Product

@Composable
fun ProductLazyList(
    allProductEntities: List<Product>,

    onProductClick: (String) -> Unit
) {
    LazyColumn {
        items(allProductEntities.size) { index ->
            val product = allProductEntities[index]
            ProductItem(product, onProductClick)
        }

    }
}
package com.iamnaran.home.presentation.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iamnaran.designsystem.theme.JantarTheme
import com.iamnaran.designsystem.theme.appTypography
import com.iamnaran.designsystem.theme.dimens
import com.iamnaran.home.data.Product

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProductDetails(
    product: Product,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onNavigateBack: () -> Unit
) {

    val context = LocalContext.current

    val imageRequest = remember(product.thumbnail) {
        ImageRequest.Builder(context)
            .data(product.thumbnail)
            .size(200, 200)
            .build()
    }

    val isExpanded by remember { mutableStateOf(false) }


    with(sharedTransitionScope) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onNavigateBack() }
                .padding(MaterialTheme.dimens.sizeNormal)
                .sharedElement(
                    rememberSharedContentState(key = "product_container_${product.id}"),
                    animatedVisibilityScope,
                    placeHolderSize = SharedTransitionScope.PlaceHolderSize.animatedSize
                )
                .shadow(
                    elevation = 5.dp,
                    spotColor = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .clickable {
                    onNavigateBack()
                },
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                Modifier
                    .fillMaxWidth(),
            ) {
                AsyncImage(
                    model = imageRequest,
                    contentDescription = "",
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .height(200.dp)
                        .sharedElement(
                            rememberSharedContentState(key = "product_image_${product.id}"),
                            animatedVisibilityScope,
                            placeHolderSize = SharedTransitionScope.PlaceHolderSize.animatedSize
                        ),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                Modifier
                    .padding(10.dp),
            ) {
                Text(
                    text = product.category,
                    style = appTypography.labelSmall,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = product.title,
                    style = appTypography.titleLarge,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = product.description,
                    style = appTypography.bodySmall,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }


}


@Preview("Login Screen")
@Composable
fun ProductDetailsPreview() {
    JantarTheme {

    }

}
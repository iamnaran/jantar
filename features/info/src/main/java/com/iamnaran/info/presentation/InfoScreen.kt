package com.iamnaran.info.presentation

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.iamnaran.info.data.getMedTabletTestData
import org.koin.androidx.compose.koinViewModel

@Composable
fun InfoScreen(capturedImageUri: String) {
    val viewModel: InfoViewModel = koinViewModel()
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val uri = Uri.parse(capturedImageUri)

    LaunchedEffect(capturedImageUri) {
        viewModel.fetchInfo(getMedTabletTestData())
    }

    when (val state = uiState.value) {
        InfoUIState.Loading -> {
            Text("Loading...")
        }
        is InfoUIState.Success -> {

            if (state.data != null){
                Text("Success: ${state.data.name}")
            }else{
                Text("No data available.")
            }
        }
        is InfoUIState.Error -> {
            Text("Error: ${state.message}")
        }
        InfoUIState.Empty -> {
            Text("No data available.")
        }
    }

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .crossfade(true)
            .build()
    )


    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,
    ) {
        Text("Image Captured")
        Image(
            painter = painter,
            contentDescription = "Captured Image Thumbnail",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
                .aspectRatio(1f)
                .height(200.dp),
            contentScale = ContentScale.Crop,
        )
    }
}
package com.iamnaran.explore.presentation.videos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(
    playerView: PlayerView,
    viewModel: ForYouViewModel
) {
    AndroidView(
        factory = { playerView },
        modifier = Modifier.fillMaxSize()
    )

}
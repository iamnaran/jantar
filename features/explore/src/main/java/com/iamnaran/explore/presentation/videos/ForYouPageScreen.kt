package com.iamnaran.explore.presentation.videos

import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.Player
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.rememberAsyncImagePainter
import com.iamnaran.explore.data.CreatorContent
import com.iamnaran.explore.presentation.videos.components.ComposableLifecycle
import com.iamnaran.explore.presentation.videos.components.PlayerListener
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForYouPageScreen() {

    val viewModel: ForYouViewModel = koinViewModel()
    val videoState = viewModel.videoState.collectAsStateWithLifecycle()


    ForYouPageContent(videoState, viewModel)

}

@Composable
fun ForYouPageContent(videoState: State<VideoUiState>, viewModel: ForYouViewModel) {
    val pagerState = rememberPagerState(pageCount = { videoState.value.videos.size })

    VerticalPager(
        pageSize = PageSize.Fill,
        state = pagerState,
        beyondViewportPageCount = 2,
        horizontalAlignment = Alignment.CenterHorizontally,
        key = {
            videoState.value.videos[it].videoUrl
        }
    ) { index ->

        if (index == pagerState.currentPage) {
            videoState.value.playMediaAt(index)
            VideoCard(
                videoState.value.player,
                videoState.value.videos[index],
                viewModel
            )
        } else {
            VideoThumbnail(video = videoState.value.videos[index])
        }

    }

}

@Composable
fun VideoThumbnail(video: CreatorContent) {
    Image(
        painter = rememberAsyncImagePainter(
            model = video.thumbnailUrl
        ),
        contentDescription = "Preview",
        modifier = Modifier.fillMaxSize()
    )

}


@Composable
fun VideoCard(
    player: Player?,
    creatorContent: CreatorContent,
    viewModel: ForYouViewModel
) {
    val context = LocalContext.current

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> viewModel.createPlayer(context)
            else -> {}
        }
    }

    var showPlayer by remember { mutableStateOf(false) }
    if (player != null) {
        PlayerListener(
            player = player
        ) { event ->
            when (event) {
                Player.EVENT_RENDERED_FIRST_FRAME -> {
                    showPlayer = true
                }

                Player.EVENT_PLAYER_ERROR -> {

                }
            }
        }
        val playerView = rememberPlayerView(player)
        VideoPlayer(
            playerView = playerView,
            viewModel = viewModel
        )
    }
    if (!showPlayer) {
        VideoThumbnail(video = creatorContent)
    }

}


@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun rememberPlayerView(player: Player): PlayerView {
    val context = LocalContext.current
    val playerView = remember {
        PlayerView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            setShowBuffering(PlayerView.SHOW_BUFFERING_NEVER)
            this.player = player
        }
    }
    DisposableEffect(key1 = player) {
        playerView.player = player
        onDispose {
            playerView.player = null
        }
    }
    return playerView
}

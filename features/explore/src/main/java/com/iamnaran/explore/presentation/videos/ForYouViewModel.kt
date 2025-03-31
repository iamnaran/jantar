package com.iamnaran.explore.presentation.videos

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.iamnaran.explore.data.CreatorContent
import com.iamnaran.explore.data.generateList
import com.iamnaran.explore.data.toMediaUriItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForYouViewModel : ViewModel() {

    private val _videoState = MutableStateFlow(VideoUiState())
    val videoState: StateFlow<VideoUiState> = _videoState

    init {
        generateForYouData()
    }

    private fun generateForYouData() {

        viewModelScope.launch {
            _videoState.emit(
                VideoUiState(
                    videos = generateList()
                )
            )

        }
    }

    fun createPlayer(context: Context) {
        _videoState.update { state ->
            if (state.player == null) {
                state.copy(player = ExoPlayer.Builder(context).build().apply {
                    repeatMode = Player.REPEAT_MODE_ONE
                    setMediaItems(state.videos.toMediaUriItems())
                    prepare()
                })
            } else
                state
        }
    }


    fun releasePlayer() {

        _videoState.update { state ->
            state.player?.release()
            state.copy(player = null)
        }
    }


}
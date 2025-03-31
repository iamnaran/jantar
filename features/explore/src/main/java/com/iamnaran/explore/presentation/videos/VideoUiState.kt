package com.iamnaran.explore.presentation.videos

import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.Player
import com.iamnaran.explore.data.CreatorContent

data class VideoUiState(
    val player: Player? = null,
    val videos: List<CreatorContent> = emptyList()
) {

    fun playMediaAt(position: Int) {
        player?.let { player->
            if (player.currentMediaItemIndex == position && player.isPlaying)
                return

            player.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                    .setUsage(C.USAGE_MEDIA)
                    .build(),
                true
            )
            player.volume = 1f
            player.seekToDefaultPosition(position)
            player.prepare()
            player.playWhenReady = true
        }
    }
}
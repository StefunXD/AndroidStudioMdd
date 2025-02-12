package com.example.mdd

import android.content.Context

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

// Correct import


class AudioPlayer (private val context: Context) {
    private var player: ExoPlayer? = null
    fun playAudio(audioFileName: String){
        player?.release()
        player = ExoPlayer.Builder(context).build().apply {
            val audioUri = Uri.parse("android.resource://${context.packageName}/raw/$audioFileName")
            val mediaItem = MediaItem.fromUri(audioUri)
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }
    fun stop() {
        player?.stop()
    }

    fun release() {
        player?.release()
        player = null

    }
}
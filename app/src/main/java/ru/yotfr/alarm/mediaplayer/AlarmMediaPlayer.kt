package ru.yotfr.alarm.mediaplayer

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import ru.yotfr.alarm.domain.model.Sound

class AlarmMediaPlayer(
    val isLooping: Boolean = false,
    val context: Context
) {
    private var mediaPlayer: MediaPlayer? = null

    private val onPreparedListener = MediaPlayer.OnPreparedListener {
        startPlayingSound()
    }

    init {
        initializeMediaPlayer()
        configureMediaPlayer()
    }

    fun playSound(sound: Sound) {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
        mediaPlayer?.reset()
        mediaPlayer?.setDataSource(
            context,
            sound.uri(context)
        )
        mediaPlayer?.prepareAsync()
    }

    fun destroyMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer()

    }

    private fun configureMediaPlayer() {
        mediaPlayer?.isLooping = isLooping
        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        mediaPlayer?.setOnPreparedListener(onPreparedListener)
    }

    private fun startPlayingSound() {
        mediaPlayer?.start()
    }

}
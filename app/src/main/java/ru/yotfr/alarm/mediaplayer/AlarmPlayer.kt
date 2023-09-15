package ru.yotfr.alarm.mediaplayer

import android.content.Context
import android.health.connect.datatypes.units.Volume
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import ru.yotfr.alarm.domain.model.Sound

class AlarmPlayer(
    val isLooping: Boolean = false,
    val context: Context,
    val soundLevel: Float? = 1f
) {
    private var mediaPlayer: MediaPlayer? = null
    private var audioManager: AudioManager? = null
    private var initialVolume: Int = 0

    private var volume = soundLevel ?: 1f

    private val onPreparedListener = MediaPlayer.OnPreparedListener {
        startPlayingSound()
    }

    init {
        initializeMediaPlayer()
        initializeAudioManager()
        configureMediaPlayer()
        initVolume()
    }

    fun configureVolume(volumeLevel: Float) {
        volume = volumeLevel
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

    fun playSoundIncreaseVolume(sound: Sound, targetVolume: Volume) {
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

    private fun initVolume() {
        audioManager?.let {
            val currentVolume = it.getStreamVolume(AudioManager.STREAM_ALARM)
            initialVolume = currentVolume
            val maxVolume = it.getStreamMaxVolume(AudioManager.STREAM_ALARM)
            val requiredInitVolume = (maxVolume * volume).toInt()
            it.setStreamVolume(AudioManager.STREAM_ALARM, requiredInitVolume, 0)
        }
    }

    fun setVolume(soundLevel: Float) {
        audioManager?.let {
            val maxVolume = it.getStreamMaxVolume(AudioManager.STREAM_ALARM)
            val requiredVolume = (maxVolume * soundLevel).toInt()
            it.setStreamVolume(AudioManager.STREAM_ALARM, requiredVolume, 0)
        }
    }

    private fun returnInitialVolume() {
        audioManager?.setStreamVolume(AudioManager.STREAM_ALARM, initialVolume, 0)
    }

    fun destroyAlarmPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
        returnInitialVolume()
        audioManager = null
    }

    private fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer()
    }

    private fun initializeAudioManager() {
        audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    private fun configureMediaPlayer() {
        mediaPlayer?.isLooping = isLooping
        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
        )
        mediaPlayer?.setOnPreparedListener(onPreparedListener)
    }

    private fun startPlayingSound() {
        mediaPlayer?.start()
    }

}
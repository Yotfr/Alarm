package ru.yotfr.alarm.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.yotfr.alarm.AlarmApp
import ru.yotfr.alarm.MainActivity
import ru.yotfr.alarm.R
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.model.getNextTriggerTime
import ru.yotfr.alarm.domain.usecase.ChangeAlarmTriggerTimeUseCase
import ru.yotfr.alarm.domain.usecase.GetAlarmByIdUseCase
import ru.yotfr.alarm.ui.navigation.NavigationConstants
import javax.inject.Inject

@AndroidEntryPoint
class AlarmService : Service() {

    @Inject
    lateinit var getAlarmByIdUseCase: GetAlarmByIdUseCase

    @Inject
    lateinit var changeAlarmTriggerTimeUseCase: ChangeAlarmTriggerTimeUseCase

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private var mediaPlayer: MediaPlayer? = null
    private val onPreparedListener = OnPreparedListener { startPlayingSound() }
    private var alarmID: Long? = null

    override fun onCreate() {
        super.onCreate()
        initializeMediaPlayer()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            if (it.getBooleanExtra(RECREATE_ACTIVITY_INTENT_EXTRA_KEY, false)) {
                alarmID?.let { id ->
                    startActivity(getStartScreenIntent(id, true))
                }
            }
            intent.getLongExtra(ALARM_ID_INTENT_EXTRA_KEY, 0L).takeIf { it != 0L }?.let { alarmId ->
                alarmID = alarmId
                scope.launch {
                    val alarm = getAlarmByIdUseCase(alarmId)
                    scheduleNewAlarm(alarm)
                }
                configureAndPrepareMediaPlayer()
                startActivity(getStartScreenIntent(alarmId))
                startForeground(alarmId.toInt(), getServiceNotification())
                AlarmRingStatus.isRinging = true
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        AlarmRingStatus.isRinging = false
        destroyMediaPlayer()
        job.cancel()
        alarmID = null
    }

    private fun getStartScreenIntent(alarmId: Long, recreate: Boolean = false): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            "${NavigationConstants.MY_URI_RING}/${NavigationConstants.ALARM_ID_ARGUMENT_KEY}=$alarmId".toUri(),
            this,
            MainActivity::class.java
        ).addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or if (recreate) Intent.FLAG_ACTIVITY_SINGLE_TOP else Intent.FLAG_ACTIVITY_CLEAR_TOP
        )
    }

    private fun getServiceNotification(): Notification {
        return NotificationCompat.Builder(
            this,
            AlarmApp.NOTIFICATION_CHANNEL_SERVICE_NOTIFICATION_ID
        )
            .setContentTitle("Alarm")
            .setContentText("Ringing...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .build()
    }

    private suspend fun scheduleNewAlarm(alarmModel: AlarmModel) {
        alarmModel.getNextTriggerTime()?.let { nextTriggerTime ->
            changeAlarmTriggerTimeUseCase(
                alarmModel.copy(
                    triggerTime = nextTriggerTime
                )
            )
        }
    }

    private fun startPlayingSound() {
        mediaPlayer?.start()
    }

    private fun configureAndPrepareMediaPlayer() {
        mediaPlayer?.setDataSource(
            this,
            Uri.parse("android.resource://" + this.packageName + "/" + R.raw.alarm)
        )
        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        mediaPlayer?.prepareAsync()
        mediaPlayer?.setOnPreparedListener(onPreparedListener)
    }

    private fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.isLooping = true
    }

    private fun destroyMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    companion object {
        const val ALARM_ID_INTENT_EXTRA_KEY = "ID"
        const val RECREATE_ACTIVITY_INTENT_EXTRA_KEY = "recreate"
    }

}
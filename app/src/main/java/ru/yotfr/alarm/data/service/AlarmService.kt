package ru.yotfr.alarm.data.service

import android.app.PendingIntent
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
import ru.yotfr.alarm.MainActivity
import ru.yotfr.alarm.R
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.repository.AlarmRepository
import ru.yotfr.alarm.domain.repository.AlarmScheduler
import ru.yotfr.alarm.ui.navigation.NavigationConstants
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class AlarmService : Service() {

    @Inject
    lateinit var alarmScheduler: AlarmScheduler
    @Inject
    lateinit var alarmRepository: AlarmRepository

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private var mediaPlayer: MediaPlayer? = null

    private val onPreparedListener = OnPreparedListener { mediaPlayer?.start() }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.isLooping = true
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            if (intent.getBooleanExtra("CANCEL", false)) {
                stopSelf()
            }
            intent.getLongExtra("ID",0L).takeIf { it != 0L }?.let {
                scope.launch {
                    val alarm = alarmRepository.getAlarmById(it)
                    scheduleNewAlarm(alarm)
                }
                mediaPlayer?.setDataSource(this, Uri.parse("android.resource://" + this.packageName + "/" + R.raw.alarm))
                mediaPlayer?.setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                mediaPlayer?.prepareAsync()
                mediaPlayer?.setOnPreparedListener(onPreparedListener)
                val notification = NotificationCompat.Builder(this, "NOTIFICATION_CHANNEL_SERVICE")
                    .setContentTitle("Alarm")
                    .setContentText("Ringing...")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setOngoing(true)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .addAction(androidx.core.R.drawable.ic_call_decline, "", getCancelIntent())
                    .setAutoCancel(true)
                    .build()
                val startScreenIntent = Intent(
                    Intent.ACTION_VIEW,
                    "${NavigationConstants.MY_URI_RING}/${NavigationConstants.ALARM_ID_ARGUMENT_KEY}=$it".toUri(),
                    this,
                    MainActivity::class.java
                ).addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                )
                startActivity(startScreenIntent)
                startForeground(it.toInt(), notification)
            }

        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun getCancelIntent(): PendingIntent {
        val intentSelf = Intent(this, AlarmService::class.java).apply {
            putExtra("CANCEL", true)
        }
        return PendingIntent.getService(this, 0, intentSelf, PendingIntent.FLAG_IMMUTABLE)
    }

    private suspend fun scheduleNewAlarm(alarmModel: AlarmModel) {
        if (alarmModel.activeDays.isEmpty()) return
        var nowDate = LocalDate.now()
        do {
            nowDate = nowDate.plusDays(1)
        } while (!alarmModel.activeDays.contains(nowDate.dayOfWeek))
        val updatedAlarmModel = alarmModel.copy(
            triggerTime = alarmModel.triggerTime.toLocalTime().atDate(nowDate)
        )
        alarmRepository.updateAlarm(updatedAlarmModel)
        alarmScheduler.cancelAlarm(updatedAlarmModel.id)
        alarmScheduler.scheduleAlarm(updatedAlarmModel.triggerTime, updatedAlarmModel.id)
    }

}
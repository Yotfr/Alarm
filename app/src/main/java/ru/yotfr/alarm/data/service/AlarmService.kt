package ru.yotfr.alarm.data.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
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
import ru.yotfr.alarm.ui.createalarm.mapper.toDayOfWeek
import ru.yotfr.alarm.ui.navigation.MY_URI_RING
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
                    MY_URI_RING.toUri(),
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
        } while (!alarmModel.activeDays.map { it.toDayOfWeek() }.contains(nowDate.dayOfWeek))
        val updatedAlarmModel = alarmModel.copy(
            triggerTime = alarmModel.triggerTime.toLocalTime().atDate(nowDate)
        )
        alarmRepository.updateAlarm(updatedAlarmModel)
        alarmScheduler.cancelAlarm(updatedAlarmModel.id)
        alarmScheduler.scheduleAlarm(updatedAlarmModel.triggerTime, updatedAlarmModel.id)
    }

}
package ru.yotfr.alarmscheduler

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import ru.yotfr.alarm.MainActivity
import ru.yotfr.alarm.receiver.AlarmBroadcastReceiver
import ru.yotfr.alarm.service.AlarmService
import ru.yotfr.receiver.AlarmBroadcastReceiver
import java.time.LocalDateTime
import java.time.ZoneId

class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @SuppressLint("MissingPermission")
    override fun scheduleAlarm(triggerTime: LocalDateTime, id: Long) {
        val alarmClockInfo = AlarmClockInfo(
            triggerTime.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            getClickIntent()
        )
        alarmManager.setAlarmClock(
            alarmClockInfo,
            getAlarmIntent(id)
        )
    }

    override fun dismissAlarm() {
        val stopServiceIntent = Intent(context, AlarmService::class.java)
        context.stopService(stopServiceIntent)
    }

    override suspend fun cancelAlarm(id: Long) {
        alarmManager.cancel(getAlarmIntent(id))
    }

    private fun getAlarmIntent(id: Long): PendingIntent {
        val broadcastIntent = Intent(
            context,
            AlarmBroadcastReceiver::class.java
        ).putExtra(AlarmBroadcastReceiver.ALARM_ID_INTENT_EXTRA_KEY, id)
        return PendingIntent.getBroadcast(
            context,
            id.toInt(),
            broadcastIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun getClickIntent(): PendingIntent {
        val activityIntent = Intent(context, MainActivity::class.java).addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        )
        return PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_IMMUTABLE)
    }
}
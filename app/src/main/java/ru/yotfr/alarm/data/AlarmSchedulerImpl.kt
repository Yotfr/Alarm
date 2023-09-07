package ru.yotfr.alarm.data

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import ru.yotfr.alarm.MainActivity
import ru.yotfr.alarm.domain.AlarmScheduler
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

    override suspend fun cancelAlarm(id: Long) {
        alarmManager.cancel(getAlarmIntent(id))
    }

    private fun getAlarmIntent(id: Long): PendingIntent {
        val broadcastIntent = Intent(context, AlarmBroadcastReceiver::class.java)
        return PendingIntent.getBroadcast(context, id.toInt(), broadcastIntent, PendingIntent.FLAG_IMMUTABLE)
    }
    private fun getClickIntent(): PendingIntent {
        val activityIntent = Intent(context, MainActivity::class.java)
        return PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_IMMUTABLE)
    }
}
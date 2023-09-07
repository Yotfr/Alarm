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
import timber.log.Timber
import java.time.LocalDateTime
import java.time.ZoneId

class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    @SuppressLint("MissingPermission")
    override fun scheduleAlarm(triggerTime: LocalDateTime, id: Long) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent =
            PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_IMMUTABLE)
        val triggerTimeN = triggerTime.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000
        Log.d("TEST","triggerTimeN $triggerTimeN ldt $triggerTime")
        val alarmClockInfo = AlarmClockInfo(
            triggerTimeN,
            activityPendingIntent
        )
        val broadcastIntent = Intent(context, AlarmBroadcastReceiver::class.java)
        val broadcastPendingIntent = PendingIntent.getBroadcast(context, id.toInt(), broadcastIntent, PendingIntent.FLAG_IMMUTABLE)
        alarmManager.setAlarmClock(
            alarmClockInfo,
            broadcastPendingIntent
        )
    }
}
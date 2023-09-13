package ru.yotfr.alarm.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import ru.yotfr.alarm.data.service.AlarmService

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { intent ->
                intent.getLongExtra(
                    ALARM_ID_INTENT_EXTRA_KEY, 0L
                ).takeIf { it != 0L }?.let { alarmId ->
                    val serviceIntent = Intent(context, AlarmService::class.java)
                        .putExtra(AlarmService.ALARM_ID_INTENT_EXTRA_KEY, alarmId)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.startForegroundService(serviceIntent)
                    } else {
                        context.startService(serviceIntent)
                    }
                }
            }
        }
    }

    companion object {
        const val ALARM_ID_INTENT_EXTRA_KEY = "ID"
    }
}
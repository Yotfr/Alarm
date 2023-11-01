package ru.yotfr.alarm.service

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.util.Log

object AlarmServiceHelper {

    const val ALARM_ID_KEY = "ALARM_ID_KEY"

    enum class ServiceCommand(val action: String) {
        ACTION_START_SERVICE("ACTION_START_SERVICE"),
        ACTION_DISMISS_ALARM("ACTION_DISMISS_ALARM"),
        ACTION_SNOOZE_ALARM("ACTION_SNOOZE_ALARM")
    }
    enum class AlarmState {
        RINGING,
        SNOOZED,
        DISMISSED,
        UNKNOWN
    }

    fun bindService(context: Context, serviceConnection: ServiceConnection) {
        Log.d("TEST","AlarmService bind")
        Intent(context, AlarmService::class.java).also { intent ->
            context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    fun unbindService(context: Context, serviceConnection: ServiceConnection) {
        Log.d("TEST","AlarmService unbind")
        context.unbindService(serviceConnection)
    }

    fun triggerServiceCommand(
        context: Context,
        command: ServiceCommand,
        alarmId: Long? = null
    ) {
        Intent(context, AlarmService::class.java).apply {
            action = command.action
            alarmId?.let {
                putExtra(ALARM_ID_KEY, it)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(this)
            } else {
                context.startService(this)
            }
        }
    }


}
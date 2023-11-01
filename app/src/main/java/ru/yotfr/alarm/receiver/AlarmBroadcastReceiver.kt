package ru.yotfr.alarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import ru.yotfr.alarm.service.AlarmService
import ru.yotfr.alarm.service.AlarmServiceHelper

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { intent ->
                intent.getLongExtra(
                    ALARM_ID_INTENT_EXTRA_KEY, 0L
                ).takeIf { it != 0L }?.let { alarmId ->
                    AlarmServiceHelper.triggerServiceCommand(
                        context = context,
                        command = AlarmServiceHelper.ServiceCommand.ACTION_START_SERVICE,
                        alarmId = alarmId
                    )
                }
            }
        }
    }

    companion object {
        const val ALARM_ID_INTENT_EXTRA_KEY = "ID"
    }
}
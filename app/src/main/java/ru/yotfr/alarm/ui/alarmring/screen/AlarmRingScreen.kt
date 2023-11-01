package ru.yotfr.alarm.ui.alarmring.screen

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import ru.yotfr.alarm.domain.model.Snooze
import ru.yotfr.alarm.service.AlarmService
import ru.yotfr.alarm.service.AlarmServiceEvent
import ru.yotfr.alarm.service.AlarmServiceHelper
import ru.yotfr.alarm.ui.utils.OnGoBackground
import ru.yotfr.alarm.ui.utils.OnReturnForeground
import ru.yotfr.alarm.ui.utils.collectWithLifecycle

@Composable
fun AlarmRingScreen(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    var alarmService by remember { mutableStateOf<AlarmService?>(null) }
    val alarm = alarmService?.alarm?.collectAsState()
    val serviceConnection by remember {
        mutableStateOf<ServiceConnection>(
            object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    val binder = service as AlarmService.AlarmBinder
                    alarmService = binder.getService()
                }
                override fun onServiceDisconnected(name: ComponentName?) {}
            }
        )
    }
    alarmService?.event?.collectWithLifecycle { alarmServiceEvent ->
        when (alarmServiceEvent) {
            AlarmServiceEvent.AlarmDismissed,
            AlarmServiceEvent.AlarmSnoozed -> {
                navigateBack()
            }
        }
    }
    OnReturnForeground {
        AlarmServiceHelper.bindService(
            context = context,
            serviceConnection = serviceConnection
        )
    }
    OnGoBackground {
        AlarmServiceHelper.unbindService(
            context = context,
            serviceConnection = serviceConnection
        )
    }

    BackHandler {}

    alarm?.value?.let {
        AlarmRingContent(
            triggerTime = it.triggerTime,
            label = it.label,
            onSnoozeClicked = {
                AlarmServiceHelper.triggerServiceCommand(
                    context = context,
                    command = AlarmServiceHelper.ServiceCommand.ACTION_SNOOZE_ALARM
                )
            },
            onDismissClicked = {
                AlarmServiceHelper.triggerServiceCommand(
                    context = context,
                    command = AlarmServiceHelper.ServiceCommand.ACTION_DISMISS_ALARM
                )
            },
            isSnoozeActive = it.snooze != Snooze.OFF
        )
    }
}


package ru.yotfr.alarm.ui.alarmring.screen

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.alarm.domain.model.Snooze
import ru.yotfr.alarm.service.AlarmRingStatus
import ru.yotfr.alarm.service.AlarmService
import ru.yotfr.alarm.ui.alarmring.event.AlarmRingEvent
import ru.yotfr.alarm.ui.alarmring.viewmodel.AlarmRingViewModel
import ru.yotfr.alarm.ui.common.OnPauseOnDestroyEffect

@Composable
fun AlarmRingScreen(
    alarmId: Long,
    vm: AlarmRingViewModel = hiltViewModel()
) {
    val alarm by vm.alarm.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.onEvent(AlarmRingEvent.EnterScreen(alarmId))
    }

    OnPauseOnDestroyEffect(
        onPause = { recreateActivity(context) },
        onDestroy = { recreateActivity(context) }
    )

    AlarmRingContent(
        triggerTime = alarm.triggerTime,
        label = alarm.label,
        onSnoozeClicked = { vm.onEvent(AlarmRingEvent.SnoozeAlarm) },
        onDismissClicked = { vm.onEvent(AlarmRingEvent.DismissAlarm) },
        isSnoozeActive = alarm.snooze != Snooze.OFF
    )
}

private fun recreateActivity(context: Context) {
    if (AlarmRingStatus.isRinging) {
        val recreateActivityServiceIntent = Intent(context, AlarmService::class.java)
            .putExtra(AlarmService.RECREATE_ACTIVITY_INTENT_EXTRA_KEY, true)
        context.startService(recreateActivityServiceIntent)
    }
}


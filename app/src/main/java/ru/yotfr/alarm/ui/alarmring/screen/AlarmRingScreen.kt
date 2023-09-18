package ru.yotfr.alarm.ui.alarmring.screen

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import ru.yotfr.model.Snooze
import ru.yotfr.service.AlarmRingStatus
import ru.yotfr.service.AlarmService
import ru.yotfr.alarm.ui.alarmring.event.AlarmRingEvent
import ru.yotfr.alarm.ui.alarmring.viewmodel.AlarmRingViewModel
import ru.yotfr.alarm.ui.common.OnPauseOnDestroyEffect
import ru.yotfr.alarm.ui.createalarm.event.CreateAlarmScreenEvent

@Composable
fun AlarmRingScreen(
    alarmId: Long,
    vm: AlarmRingViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val alarm by vm.alarm.collectAsState()
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(Unit) {
        vm.onEvent(AlarmRingEvent.EnterScreen(alarmId))
    }

    OnPauseOnDestroyEffect(
        onPause = { recreateActivity(context) },
        onDestroy = { recreateActivity(context) }
    )

    BackHandler {}

    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            vm.event.collectLatest {
                when (it) {
                    CreateAlarmScreenEvent.NavigateBack -> {
                        navigateBack()
                    }
                }
            }
        }
    }

    AlarmRingContent(
        triggerTime = alarm.triggerTime,
        label = alarm.label,
        onSnoozeClicked = { vm.onEvent(AlarmRingEvent.SnoozeAlarm) },
        onDismissClicked = { vm.onEvent(AlarmRingEvent.DismissAlarm) },
        isSnoozeActive = alarm.snooze != ru.yotfr.model.Snooze.OFF
    )
}

private fun recreateActivity(context: Context) {
    if (ru.yotfr.service.AlarmRingStatus.isRinging) {
        val recreateActivityServiceIntent = Intent(context, ru.yotfr.service.AlarmService::class.java)
            .putExtra(ru.yotfr.service.AlarmService.RECREATE_ACTIVITY_INTENT_EXTRA_KEY, true)
        context.startService(recreateActivityServiceIntent)
    }
}


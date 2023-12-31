package ru.yotfr.alarm.ui.createalarm.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import ru.yotfr.alarm.ui.createalarm.event.CreateAlarmEvent
import ru.yotfr.alarm.ui.createalarm.event.CreateAlarmScreenEvent
import ru.yotfr.alarm.ui.createalarm.mapper.remainTime
import ru.yotfr.alarm.ui.createalarm.viewmodel.CreateAlarmViewModel

@Composable
fun CreateAlarmScreen(
    vm: CreateAlarmViewModel = hiltViewModel(),
    alarmId: Long?,
    navigateBack: () -> Unit
) {

    val alarm by vm.screenState.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(Unit) {
        Log.d("TEST","Enter Screen launched")
        vm.onEvent(CreateAlarmEvent.EnterScreen(alarmId))
    }

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

    CreateAlarmContent(
        remainTime = alarm.triggerTime.remainTime(),
        onBackPressed = navigateBack,
        activeWeekDays = alarm.activeDays,
        onWeekDayClicked = { vm.onEvent(CreateAlarmEvent.OnWeekDayClicked(it)) },
        onSaveClicked = { vm.onEvent(CreateAlarmEvent.SaveClicked) },
        onTimeChanged = { vm.onEvent(CreateAlarmEvent.OnTriggerTimeChanged(it)) },
        onLabelChanged = { vm.onEvent(CreateAlarmEvent.OnLabelChanged(it)) },
        onSnoozeChanged = { vm.onEvent(CreateAlarmEvent.OnSnoozeChanged(it)) },
        label = alarm.label,
        onClearedWeekDays = { vm.onEvent(CreateAlarmEvent.WeekDaysCleared) },
        snooze = alarm.snooze,
        sound = alarm.sound,
        vibrate = alarm.vibrate,
        onSoundChanged = { vm.onEvent(CreateAlarmEvent.OnSoundChanged(it)) },
        onVibrateChanged = { vm.onEvent(CreateAlarmEvent.OnVibrateChanged(it)) },
        soundLevel = alarm.volume,
        onSoundLevelChanged = { vm.onEvent(CreateAlarmEvent.SoundLevelChanged(it)) }
    )
}
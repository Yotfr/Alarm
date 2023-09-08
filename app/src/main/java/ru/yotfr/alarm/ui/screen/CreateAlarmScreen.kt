package ru.yotfr.alarm.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import ru.yotfr.alarm.custom.TimePicker
import ru.yotfr.alarm.ui.event.CreateAlarmEvent
import ru.yotfr.alarm.ui.viewmodel.CreateAlarmViewModel

@Composable
fun CreateAlarmScreen(
    vm: CreateAlarmViewModel = hiltViewModel(),
    alarmId: Long?,
    navigateBack: () -> Unit
) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(Unit) {
        vm.enterScreen(alarmId)
    }

    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            vm.event.collectLatest {
                when (it) {
                    CreateAlarmEvent.NavigateBack -> {
                        navigateBack()
                    }
                }
            }
        }
    }

    val alarm by vm.screenState.collectAsState()

    val currentHour = remember { mutableStateOf(0) }
    val currentMinute = remember { mutableStateOf(0) }

    LaunchedEffect(alarm) {
        alarm?.let {
            currentHour.value = it.triggerTime.hour
            currentMinute.value = it.triggerTime.minute
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TimePicker(
                state = currentHour,
                range = 0..23,
                onStateChanged = { currentHour.value = it }
            )
            Spacer(modifier = Modifier.width(10.dp))
            TimePicker(
                state = currentMinute,
                range = 0..59,
                onStateChanged = { currentMinute.value = it }
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        TextButton(onClick = {
            vm.applyChanges(
                hour = currentHour.value,
                minute = currentMinute.value,
            )
        }) {
            Text(text = "Create")
        }
    }


}
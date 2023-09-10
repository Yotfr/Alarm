package ru.yotfr.alarm.ui.alarmlist.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.alarm.ui.alarmlist.event.AlarmListEvent
import ru.yotfr.alarm.ui.alarmlist.viewmodel.AlarmListViewModel


@Composable
fun AlarmsListScreen(
    vm: AlarmListViewModel = hiltViewModel(),
    navigateToCreateAlarmScreen: (Long?) -> Unit
) {
    val state by vm.screenState.collectAsState()

    AlarmListContent(
        alarms = state.alarms,
        onPlusClicked = { navigateToCreateAlarmScreen(null) },
        onAlarmClicked = { navigateToCreateAlarmScreen(it.id) },
        switchChecked = { alarm, newValue ->
            vm.onEvent(AlarmListEvent.ToggleAlarm(alarm, newValue))
        },
        onDeleteClicked = { vm.onEvent(AlarmListEvent.DeleteAlarm(it)) }
    )

}







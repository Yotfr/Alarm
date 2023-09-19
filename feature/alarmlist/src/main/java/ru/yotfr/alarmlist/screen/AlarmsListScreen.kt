package ru.yotfr.alarmlist.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.alarmlist.event.AlarmListEvent
import ru.yotfr.alarmlist.viewmodel.AlarmListViewModel


@Composable
fun AlarmsListScreen(
    vm: AlarmListViewModel = hiltViewModel(),
    navigateToCreateAlarmScreen: (Long?) -> Unit
) {
    val state by vm.screenState.collectAsState()

    AlarmListContent(
        alarms = state.alarms,
        onFABClicked = {
            Log.d("TEST"," FAB clicked Null")
            navigateToCreateAlarmScreen(null)
                       },
        onAlarmClicked = {
            Log.d("TEST"," FAB clicked ID")
            navigateToCreateAlarmScreen(it.id)
                         },
        switchChecked = { alarm, newValue ->
            vm.onEvent(AlarmListEvent.ToggleAlarm(alarm, newValue))
        },
        onDeleteClicked = { vm.onEvent(AlarmListEvent.DeleteAlarm(it)) }
    )
}







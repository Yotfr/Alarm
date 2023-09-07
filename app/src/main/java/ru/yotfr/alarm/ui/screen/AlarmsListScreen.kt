package ru.yotfr.alarm.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.alarm.domain.AlarmModel
import ru.yotfr.alarm.ui.viewmodel.AlarmListViewModel
import java.time.format.DateTimeFormatter

@Composable
fun AlarmsListScreen(
    vm: AlarmListViewModel = hiltViewModel(),
    navigateToCreateAlarmScreen: () -> Unit
) {
    val state by vm.screenState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state.alarms) { alarmModel ->
                AlarmItem(
                    alarmModel = alarmModel,
                    onToggle = {
                        vm.toggleAlarm(alarmModel, !it)
                    },
                    delete = {
                        vm.deleteAlarm(alarmModel)
                    }
                )
            }
        }
        FloatingActionButton(
            onClick = navigateToCreateAlarmScreen,
            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = null
            )
        }
    }

}

@Composable
fun AlarmItem(
    alarmModel: AlarmModel,
    onToggle: (Boolean) -> Unit,
    delete: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = alarmModel.triggerTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        )
        IconButton(onClick = delete) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = null
            )
        }
        Switch(
            checked = alarmModel.isActive,
            onCheckedChange = onToggle
        )
    }
}
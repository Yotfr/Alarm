package ru.yotfr.alarm.ui.alarmlist.screen

import AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.domain.model.AlarmModel
import java.time.LocalDateTime

@Preview
@Composable
fun AlarmListContentPreview() {
    val alarm = AlarmModel(
        id = 0L,
        triggerTime = LocalDateTime.now(),
        isActive = true,
        activeDays = emptyList()
    )
    val alarms = listOf(
        alarm, alarm, alarm, alarm, alarm, alarm, alarm
    )
    AlarmTheme {
        AlarmListContent(
            alarms = alarms,
            onPlusClicked = {},
            onAlarmClicked = {},
            switchChecked = { _, _ -> }, {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListContent(
    alarms: List<AlarmModel>,
    onPlusClicked: () -> Unit,
    onAlarmClicked: (AlarmModel) -> Unit,
    switchChecked: (AlarmModel, Boolean) -> Unit,
    onDeleteClicked: (AlarmModel) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AlarmTheme.colors.background
            ),
        floatingActionButton = {

        },
        topBar = { AlarmListTopAppBar() },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(22.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
//            items(alarms) { alarmModel ->
//                AlarmItem (
//                    alarmModel = alarmModel,
//                    switchCheckedChange = { switchChecked(alarmModel, it) },
//                    onClick = onAlarmClicked,
//                    onDeleteClicked = onDeleteClicked
//                )
//            }
        }
    }
}
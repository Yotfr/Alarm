@file:OptIn(ExperimentalMaterial3Api::class)

package ru.yotfr.alarm.ui.createalarm.screen

import ru.yotfr.designsystem.theme.AlarmTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.model.AlarmModel
import ru.yotfr.model.Snooze
import ru.yotfr.model.Sound
import java.time.DayOfWeek
import java.time.LocalTime

@Preview
@Composable
fun CreateAlarmContentPreview() {
    ru.yotfr.designsystem.theme.AlarmTheme {
        val activeList = remember { mutableStateListOf(DayOfWeek.MONDAY, DayOfWeek.SATURDAY) }
        var alarm by remember { mutableStateOf(ru.yotfr.model.AlarmModel()) }
        var snooze by remember { mutableStateOf(ru.yotfr.model.Snooze.FIVE) }
        CreateAlarmContent(
            remainTime = "1 day",
            onBackPressed = {},
            activeWeekDays = activeList,
            onWeekDayClicked = {
                if (activeList.contains(it)) {
                    activeList.remove(it)
                } else {
                    activeList.add(it)
                }
            }, {}, {},
            onLabelChanged = {
                alarm = alarm.copy(
                    label = it
                )
            },
            onSnoozeChanged = { snooze = it },
            label = "",
            onClearedWeekDays = { activeList.removeAll { true } },
            snooze = ru.yotfr.model.Snooze.OFF,
            sound = ru.yotfr.model.Sound.FIRST,
            onSoundChanged = {},
            onVibrateChanged = {},
            vibrate = false,
            soundLevel = 43f,
            onSoundLevelChanged = {}
        )
    }
}

@Composable
fun CreateAlarmContent(
    remainTime: String,
    onBackPressed: () -> Unit,
    activeWeekDays: List<DayOfWeek>,
    onWeekDayClicked: (DayOfWeek) -> Unit,
    onSaveClicked: () -> Unit,
    onTimeChanged: (LocalTime) -> Unit,
    label: String,
    onLabelChanged: (String) -> Unit,
    onSnoozeChanged: (ru.yotfr.model.Snooze) -> Unit,
    onClearedWeekDays: () -> Unit,
    snooze: ru.yotfr.model.Snooze,
    sound: ru.yotfr.model.Sound,
    onSoundChanged: (ru.yotfr.model.Sound) -> Unit,
    vibrate: Boolean,
    onVibrateChanged: (Boolean) -> Unit,
    soundLevel: Float,
    onSoundLevelChanged: (Float) -> Unit
) {
    Scaffold(
        topBar = {
            CreateAlarmTopAppBar(
                remainTime = remainTime,
                onBackPressed = onBackPressed
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(rememberNestedScrollInteropConnection()),
        containerColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.background,
        floatingActionButton = {
            CreateAlarmFAB(
                onClick = onSaveClicked
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TimePicker(
                onTimeChanged = onTimeChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
            WeekDaySection(
                activeWeekDays = activeWeekDays,
                onWeekDayClicked = onWeekDayClicked,
                onClearWeekDaysClicked = onClearedWeekDays
            )
            Spacer(modifier = Modifier.height(16.dp))
            LabelSection(
                value = label,
                onValueChange = onLabelChanged
            )
            Spacer(modifier = Modifier.height(16.dp))
            SnoozeSoundSection(
                snooze = snooze,
                onSnoozeChanged = onSnoozeChanged,
                sound = sound,
                onSoundChanged = onSoundChanged,
                vibrate = vibrate,
                onVibrateChange = onVibrateChanged,
                soundLevel = soundLevel,
                onSoundLevelChanged = onSoundLevelChanged
            )
            Spacer(modifier = Modifier.height(100.dp))
        }
    }

}
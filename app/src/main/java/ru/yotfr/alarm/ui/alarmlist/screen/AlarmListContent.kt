package ru.yotfr.alarm.ui.alarmlist.screen

import AlarmTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.R
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.model.WeekDays
import java.time.LocalDateTime

@Preview
@Composable
fun AlarmListContentPreview() {
    fun alarm(id: Long) = AlarmModel(
        id = id,
        triggerTime = LocalDateTime.now(),
        isActive = true,
        activeDays = emptyList()
    )

    fun testAlarm(id: Long) = AlarmModel(
        id = id,
        triggerTime = LocalDateTime.now(),
        isActive = true,
        activeDays = listOf(
            WeekDays.FRIDAY, WeekDays.MONDAY
        )
    )

    var alarms by remember {
        mutableStateOf(
            listOf(
                alarm(0), testAlarm(1), alarm(2), testAlarm(3),
                alarm(4), alarm(5), alarm(6), testAlarm(7),
                alarm(8), testAlarm(9), testAlarm(10), alarm(11)
            )
        )
    }
    AlarmTheme {
        AlarmListContent(
            alarms = alarms,
            onFABClicked = {},
            onAlarmClicked = {},
            switchChecked = { alarm, state ->
                alarms = alarms.map {
                    if (it.id == alarm.id) {
                        it.copy(
                            isActive = state
                        )
                    } else it
                }
            },
            onDeleteClicked = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListContent(
    alarms: List<AlarmModel>,
    onFABClicked: () -> Unit,
    onAlarmClicked: (AlarmModel) -> Unit,
    switchChecked: (AlarmModel, Boolean) -> Unit,
    onDeleteClicked: (AlarmModel) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var isFABVisible by remember { mutableStateOf(true) }
    var isInEditMode by remember { mutableStateOf(false) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -1) {
                    isFABVisible = false
                }
                if (available.y > 1) {
                    isFABVisible = true
                }
                return Offset.Zero
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = AlarmTheme.colors.background,
        floatingActionButton = {
            AlarmFAB(
                onClick = onFABClicked,
                isVisible = isFABVisible
            )
        },
        topBar = {
            AlarmListTopAppBar(
                scrollBehavior = scrollBehavior,
                onEditClicked = {
                    isInEditMode = !isInEditMode
                },
                isInEditMode = isInEditMode
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = alarms.isEmpty(),
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = stringResource(id = R.string.no_alarms),
                    style = AlarmTheme.typography.headline,
                    color = AlarmTheme.colors.text,
                    textAlign = TextAlign.Center
                )
            }
            LazyColumn(
                contentPadding = PaddingValues(22.dp),
                verticalArrangement = Arrangement.spacedBy(22.dp),
                modifier = Modifier
                    .padding(paddingValues)
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .nestedScroll(nestedScrollConnection)
            ) {
                items(alarms) { alarmModel ->
                    AlarmItem(
                        alarmModel = alarmModel,
                        onActiveChanged = {
                            switchChecked(alarmModel, it)
                        },
                        onClick = {
                            onAlarmClicked(alarmModel)
                        },
                        isInEditMode = isInEditMode,
                        onDeleteClicked = {
                            onDeleteClicked(alarmModel)
                        }
                    )
                }
            }
        }
    }
}
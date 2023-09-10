@file:OptIn(ExperimentalMaterial3Api::class)

package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.yotfr.alarm.R
import ru.yotfr.alarm.domain.model.WeekDays
import java.time.LocalTime

@Preview
@Composable
fun CreateAlarmContentPreview() {
    AlarmTheme {
        val activeList = remember { mutableStateListOf(WeekDays.MONDAY, WeekDays.SATURDAY) }
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
            }, {}, {}
        )
    }
}

@Composable
fun CreateAlarmContent(
    remainTime: String,
    onBackPressed: () -> Unit,
    activeWeekDays: List<WeekDays>,
    onWeekDayClicked: (WeekDays) -> Unit,
    onSaveClicked: () -> Unit,
    onTimeChanged: (LocalTime) -> Unit
) {
    Scaffold(
        topBar = {
            CreateAlarmTopAppBar(
                remainTime = remainTime,
                onBackPressed = onBackPressed
            )
        },
        modifier = Modifier.fillMaxSize(),
        contentColor = Color.Transparent,
        floatingActionButton = {
            Button(onClick = onSaveClicked) {
                Text(
                    text = stringResource(id = R.string.save),
                    fontSize = 18.sp,
                    color = AlarmTheme.colors.text,
                    textAlign = TextAlign.Start
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(22.dp))
            TimePicker(
                modifier = Modifier,
                onTimeChanged = onTimeChanged
            )
            Spacer(modifier = Modifier.height(42.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = AlarmTheme.colors.surface,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                    .border(
                        1.dp,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                        color = Color.White
                    )
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = stringResource(id = R.string.repeat),
                    fontSize = 18.sp,
                    color = AlarmTheme.colors.text,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(16.dp))
                WeekDayRow(
                    activeWeekDays = activeWeekDays,
                    onWeekDayClicked = onWeekDayClicked
                )
            }
        }
    }

}
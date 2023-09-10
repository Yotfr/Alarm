package ru.yotfr.alarm.ui.alarmlist.screen

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ru.yotfr.alarm.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.alarms),
                fontSize = 18.sp,
                color = AlarmTheme.colors.text
            )
        }
    )
}
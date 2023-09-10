package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.yotfr.alarm.R

@Preview
@Composable
fun CreateAlarmTopAppBarPreview() {
    AlarmTheme {
        CreateAlarmTopAppBar(remainTime = "20 minutes", onBackPressed = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAlarmTopAppBar(
    remainTime: String,
    onBackPressed: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = remainTime,
                fontSize = 18.sp,
                color = AlarmTheme.colors.text,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = AlarmTheme.colors.text
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = AlarmTheme.colors.background
        )
    )
}
package ru.yotfr.alarm.ui.createalarm.screen

import ru.yotfr.designsystem.theme.AlarmTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CreateAlarmTopAppBarPreview() {
    ru.yotfr.designsystem.theme.AlarmTheme {
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
                style = ru.yotfr.designsystem.theme.AlarmTheme.typography.headline,
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onBackPressed() },
                tint = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
        )
    )
}
package ru.yotfr.alarm.ui.alarmring.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AlarmRingContent(
    triggerTime: LocalDateTime,
    label: String,
    onSnoozeClicked: () -> Unit,
    onDismissClicked: () -> Unit,
    isSnoozeActive: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AlarmTheme.colors.background)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        TimeSection(
            modifier = Modifier.weight(3f),
            time = triggerTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        )
        Spacer(modifier = Modifier.height(16.dp))
        LabelSection(
            modifier = Modifier.weight(3f),
            label = label
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonSection(
            modifier = Modifier.weight(2f),
            onSnoozeClicked = onSnoozeClicked,
            onDismissClicked = onDismissClicked,
            isSnoozeActive = isSnoozeActive
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}
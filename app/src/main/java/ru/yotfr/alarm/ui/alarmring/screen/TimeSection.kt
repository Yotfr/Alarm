package ru.yotfr.alarm.ui.alarmring.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.yotfr.designsystem.component.Shape
import ru.yotfr.designsystem.component.punchedShadow

@Composable
fun TimeSection(
    modifier: Modifier,
    time: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .punchedShadow(
                offsetX = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                offsetY = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                blurRadius = ru.yotfr.designsystem.theme.AlarmTheme.shadowBlurRadius.default,
                shape = ru.yotfr.designsystem.component.Shape.RoundedRect,
                darkColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.darkShadow,
                lightColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.lightShadow,
                cornerRadius = ru.yotfr.designsystem.theme.AlarmTheme.shapeCornerRadius.default
            )
            .clip(ru.yotfr.designsystem.theme.AlarmTheme.shape.default)
            .background(
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
            )
            .padding(
                horizontal = 16.dp,
                vertical = 64.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = time,
            style = ru.yotfr.designsystem.theme.AlarmTheme.typography.displayLarge,
            color = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
        )
    }
}
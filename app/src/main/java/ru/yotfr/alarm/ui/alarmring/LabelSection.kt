package ru.yotfr.alarm.ui.alarmring

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
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.punchedShadow

@Composable
fun LabelSection(
    modifier: Modifier,
    label: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .punchedShadow(
                offsetX = AlarmTheme.shadowOffset.default,
                offsetY = AlarmTheme.shadowOffset.default,
                blurRadius = AlarmTheme.shadowBlurRadius.default,
                shape = Shape.RoundedRect,
                darkColor = AlarmTheme.colors.darkShadow,
                lightColor = AlarmTheme.colors.lightShadow,
                cornerRadius = AlarmTheme.shapeCornerRadius.default
            )
            .clip(AlarmTheme.shape.default)
            .background(
                color = AlarmTheme.colors.background
            )
            .padding(
                horizontal = 16.dp,
                vertical = 64.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = AlarmTheme.typography.headline,
            color = AlarmTheme.colors.text
        )
    }
}
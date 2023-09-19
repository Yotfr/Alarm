package ru.yotfr.alarm.ui.createalarm.screen

import ru.yotfr.designsystem.theme.AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import ru.yotfr.designsystem.component.Shape
import ru.yotfr.designsystem.component.punchedShadow
import java.time.LocalTime

@Preview
@Composable
fun PreviewNumberPicker() {
    ru.yotfr.designsystem.theme.AlarmTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TimePicker(
                onTimeChanged = {}
            )
        }
    }
}

@Composable
fun TimePicker(
    onTimeChanged: (LocalTime) -> Unit
) {
    WheelTimePicker(
        modifier = Modifier
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
        timeFormat = TimeFormat.HOUR_24,
        textStyle = ru.yotfr.designsystem.theme.AlarmTheme.typography.display,
        textColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.text,
        onSnappedTime = onTimeChanged,
        selectorProperties = WheelPickerDefaults.selectorProperties(
            enabled = false
        )
    )
}


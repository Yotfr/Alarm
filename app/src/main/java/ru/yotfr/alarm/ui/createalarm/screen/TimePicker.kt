package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
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
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.punchedShadow
import java.time.LocalTime

@Preview
@Composable
fun PreviewNumberPicker() {
    AlarmTheme {
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
        timeFormat = TimeFormat.HOUR_24,
        textStyle = AlarmTheme.typography.display,
        textColor = AlarmTheme.colors.text,
        onSnappedTime = onTimeChanged,
        selectorProperties = WheelPickerDefaults.selectorProperties(
            enabled = false
        )
    )
}


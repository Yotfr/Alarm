package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalTime

@Preview
@Composable
fun PreviewNumberPicker() {
    AlarmTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            TimePicker(
                modifier = Modifier,
                onTimeChanged = {}
            )
        }
    }
}

@Composable
fun TimePicker(
    modifier: Modifier,
    onTimeChanged: (LocalTime) -> Unit
) {
    WheelTimePicker(
        modifier = modifier,
        timeFormat = TimeFormat.HOUR_24,
        textStyle = TextStyle(
            color = AlarmTheme.colors.text,
            fontSize = 34.sp
        ),
        textColor = AlarmTheme.colors.text,
        onSnappedTime = onTimeChanged,
        selectorProperties = WheelPickerDefaults.selectorProperties(
            enabled = false
        )
    )
}


package ru.yotfr.alarm.ui.alarmlist.screen

import AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.model.AlarmModel
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.punchedShadow
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun AlarmItemPreview() {
    var alarmModel by remember {
        mutableStateOf(
            ru.yotfr.model.AlarmModel(
                activeDays = listOf(
                    DayOfWeek.SUNDAY,
                    DayOfWeek.FRIDAY
                )
            )
        )
    }
    AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AlarmTheme.colors.background)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            AlarmItem(
                alarmModel = alarmModel,
                onActiveChanged = { newState ->
                    alarmModel = alarmModel.copy(
                        isActive = newState
                    )
                },
                onClick = {},
                onDeleteClicked = {}
            )
        }
    }
}

@Composable
fun AlarmItem(
    alarmModel: ru.yotfr.model.AlarmModel,
    onActiveChanged: (Boolean) -> Unit,
    onClick: () -> Unit,
    isInEditMode: Boolean = false,
    onDeleteClicked: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val modifier = if (isInEditMode) {
            Modifier
                .weight(1f)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onClick() }
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
                    vertical = 12.dp
                )
        } else {
            Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onClick() }
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
                    vertical = 12.dp
                )
        }
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = alarmModel.triggerTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                style = AlarmTheme.typography.display,
                color = if (alarmModel.isActive) AlarmTheme.colors.text
                else AlarmTheme.colors.disabledText
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActiveWeekDaysText(
                    activeDays = alarmModel.activeDays,
                    triggerTime = alarmModel.triggerTime,
                    isActive = alarmModel.isActive
                )
                Spacer(modifier = Modifier.width(16.dp))
                AlarmSwitch(
                    isChecked = alarmModel.isActive,
                    onCheckedChange = onActiveChanged
                )
            }
        }
        if (isInEditMode) {
            IconButton(onClick = onDeleteClicked) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = AlarmTheme.colors.accent
                )
            }
        }
    }
}
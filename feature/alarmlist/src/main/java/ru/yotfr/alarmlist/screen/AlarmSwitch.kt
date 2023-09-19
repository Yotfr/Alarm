package ru.yotfr.alarmlist.screen

import AlarmTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.angledGradient
import ru.yotfr.alarm.ui.common.pressedShadow
import ru.yotfr.alarm.ui.common.punchedShadow

@Composable
fun AlarmSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .height(24.dp)
            .width(48.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onCheckedChange(!isChecked)
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(16.dp)
                .clip(AlarmTheme.shape.small)
                .pressedShadow(
                    offsetX = AlarmTheme.shadowOffset.default,
                    offsetY = AlarmTheme.shadowOffset.default,
                    blurRadius = AlarmTheme.shadowBlurRadius.default,
                    darkColor = AlarmTheme.colors.darkShadow,
                    lightColor = AlarmTheme.colors.lightShadow,
                    shape = Shape.RoundedRect,
                    cornerRadius = AlarmTheme.shapeCornerRadius.default,
                    backgroundColor = if (isChecked) AlarmTheme.colors.accent
                    else AlarmTheme.colors.background
                )
        )
        Box(
            modifier = Modifier
                .size(24.dp)
                .align(
                    if (isChecked) Alignment.CenterEnd else Alignment.CenterStart
                )
                .punchedShadow(
                    offsetX = AlarmTheme.shadowOffset.default,
                    offsetY = AlarmTheme.shadowOffset.default,
                    blurRadius = AlarmTheme.shadowBlurRadius.default,
                    shape = Shape.Circle,
                    darkColor = AlarmTheme.colors.darkShadow,
                    lightColor = AlarmTheme.colors.lightShadow,
                )
                .clip(CircleShape)
                .angledGradient(
                    colors = listOf(
                        AlarmTheme.colors.disabledText,
                        AlarmTheme.colors.text
                    ),
                    angle = 315f
                )
        )
    }
}
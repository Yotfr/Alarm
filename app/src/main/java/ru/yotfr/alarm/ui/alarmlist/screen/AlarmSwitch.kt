package ru.yotfr.alarm.ui.alarmlist.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlarmSwitch(
    active: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .width(39.dp)
            .height(21.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onCheckedChange(!active)
            }
    ) {
        Surface(
            modifier = if (active) {
                Modifier
                    .width(33.dp)
                    .height(14.dp)
                    .align(Alignment.Center)
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        clip = false,
                        spotColor = AlarmTheme.colors.primary
                    )
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                AlarmTheme.colors.primary,
                                AlarmTheme.colors.primarySecond
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            } else {
                Modifier
                    .width(33.dp)
                    .height(14.dp)
                    .align(Alignment.Center)
                    .background(
                        color = AlarmTheme.colors.shadow,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                AlarmTheme.colors.shadow,
                                AlarmTheme.colors.accentBorder
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            },
            color = Color.Transparent
        ) {}
        Surface(
            shape = CircleShape,
            modifier = if (active) {
                Modifier
                    .width(21.dp)
                    .height(21.dp)
                    .align(Alignment.CenterEnd)
                    .background(
                        color = AlarmTheme.colors.surface,
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        clip = false,
                        spotColor = AlarmTheme.colors.shadow
                    )
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                AlarmTheme.colors.accentBorder,
                                AlarmTheme.colors.shadow
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            } else {
                Modifier
                    .width(21.dp)
                    .height(21.dp)
                    .align(Alignment.CenterStart)
                    .background(
                        color = AlarmTheme.colors.surface,
                        shape = CircleShape
                    )
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        clip = false,
                        spotColor = AlarmTheme.colors.shadow
                    )
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                AlarmTheme.colors.accentBorder,
                                AlarmTheme.colors.shadow
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            },
            color = Color.Transparent
        ) {}
    }
}
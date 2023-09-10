package ru.yotfr.alarm.ui.alarmlist.screen

import AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AlarmFABPreview() {
    AlarmTheme() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            AlarmFAB({})
        }

    }
}

@Composable
fun AlarmFAB(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(78.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
            .shadow(
                elevation = 8.dp,
                shape = CircleShape,
                clip = false,
                spotColor = AlarmTheme.colors.shadow
            ),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .size(78.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            AlarmTheme.colors.primary,
                            AlarmTheme.colors.surface,
                        )
                    ),
                    shape = CircleShape
                ),
            color = Color.Transparent,
            shape = CircleShape
        ) {}
        Surface(
            modifier = Modifier
                .size(64.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AlarmTheme.colors.shadow.copy(
                                alpha = 0.6f
                            ),
                            AlarmTheme.colors.shadow
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    ),
                    shape = CircleShape
                ),
            color = Color.Transparent,
            shape = CircleShape
        ) {}
        Surface(
            modifier = Modifier
                .size(60.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AlarmTheme.colors.primary,
                            AlarmTheme.colors.primarySecond
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    ),
                    shape = CircleShape
                )
                .border(
                    width = 3.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AlarmTheme.colors.primarySecond,
                            AlarmTheme.colors.primary
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    ),
                    shape = CircleShape
                )
            ,
            color = Color.Transparent,
            shape = CircleShape
        ) {}
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            tint = AlarmTheme.colors.background
        )
    }
}
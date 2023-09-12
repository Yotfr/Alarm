package ru.yotfr.alarm.ui.alarmlist.screen

import AlarmTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.angledGradient
import ru.yotfr.alarm.ui.common.punchedShadow

@Preview
@Composable
fun AlarmFabPreview() {
    AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AlarmTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            AlarmFAB(
                onClick = {},
                isVisible = true
            )
        }
    }
}

@Composable
fun AlarmFAB(
    onClick: () -> Unit,
    isVisible: Boolean
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it * 2 }),
        exit = slideOutVertically(targetOffsetY = { it * 2 })
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onClick() }
                .punchedShadow(
                    offsetX = AlarmTheme.shadowOffset.extraLarge,
                    offsetY = AlarmTheme.shadowOffset.extraLarge,
                    blurRadius = AlarmTheme.shadowBlurRadius.large,
                    shape = Shape.RoundedRect,
                    darkColor = AlarmTheme.colors.darkShadow,
                    lightColor = AlarmTheme.colors.lightShadow,
                    cornerRadius = AlarmTheme.shapeCornerRadius.default
                )
                .clip(AlarmTheme.shape.default)
                .background(
                    color = AlarmTheme.colors.accent
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = AlarmTheme.colors.background
            )
        }
    }
}

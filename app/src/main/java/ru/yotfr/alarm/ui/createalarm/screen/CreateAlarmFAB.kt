package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.R
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.angledGradient
import ru.yotfr.alarm.ui.common.punchedShadow

@Preview
@Composable
fun CreateFabPreview() {
    AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AlarmTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            CreateAlarmFAB(
                onClick = {},
                gradient = true
            )
        }
    }
}

@Composable
fun CreateAlarmFAB(
    onClick: () -> Unit,
    //TODO: DELETE
    gradient: Boolean = false
) {
    val modifier = if (gradient) {
        Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
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
            .angledGradient(
                colors = listOf(
                    AlarmTheme.colors.disabledAccent,
                    AlarmTheme.colors.accent
                ),
                angle = 315f
            )
    } else {
        Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
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
            )
    }
    Box(
        modifier = modifier
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.save),
            style = AlarmTheme.typography.headline,
            color = AlarmTheme.colors.background
        )
    }
}
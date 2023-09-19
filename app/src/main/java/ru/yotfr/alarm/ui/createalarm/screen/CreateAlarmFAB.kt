package ru.yotfr.alarm.ui.createalarm.screen

import ru.yotfr.designsystem.theme.AlarmTheme
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
import ru.yotfr.designsystem.component.Shape
import ru.yotfr.designsystem.component.punchedShadow

@Preview
@Composable
fun CreateFabPreview() {
    ru.yotfr.designsystem.theme.AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            CreateAlarmFAB(
                onClick = {}
            )
        }
    }
}

@Composable
fun CreateAlarmFAB(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .punchedShadow(
                offsetX = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.extraLarge,
                offsetY = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.extraLarge,
                blurRadius = ru.yotfr.designsystem.theme.AlarmTheme.shadowBlurRadius.large,
                shape = ru.yotfr.designsystem.component.Shape.RoundedRect,
                darkColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.darkShadow,
                lightColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.lightShadow,
                cornerRadius = ru.yotfr.designsystem.theme.AlarmTheme.shapeCornerRadius.default
            )
            .clip(ru.yotfr.designsystem.theme.AlarmTheme.shape.default)
            .background(
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.accent
            )
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.save),
            style = ru.yotfr.designsystem.theme.AlarmTheme.typography.headline,
            color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
        )
    }
}
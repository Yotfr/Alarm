package ru.yotfr.alarm.ui.alarmring

import AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.R
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.punchedShadow

@Composable
fun ButtonSection(
    modifier: Modifier,
    onSnoozeClicked: () -> Unit,
    onDismissClicked: () -> Unit,
    isSnoozeActive: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onSnoozeClicked()
                }
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
                    color = AlarmTheme.colors.text
                )
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.snooze),
                style = AlarmTheme.typography.headline,
                color = if (isSnoozeActive)  AlarmTheme.colors.background else AlarmTheme.colors.disabledBackground
            )
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onDismissClicked()
                }
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
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.dismiss),
                style = AlarmTheme.typography.headline,
                color = AlarmTheme.colors.background
            )
        }
    }
}
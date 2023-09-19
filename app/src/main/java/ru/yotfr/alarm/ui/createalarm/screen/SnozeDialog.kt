package ru.yotfr.alarm.ui.createalarm.screen

import ru.yotfr.designsystem.theme.AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.yotfr.alarm.R
import ru.yotfr.model.Snooze
import ru.yotfr.designsystem.component.Shape
import ru.yotfr.designsystem.component.pressedShadow
import ru.yotfr.designsystem.component.punchedShadow
import ru.yotfr.alarm.ui.createalarm.mapper.stringResource

@Preview
@Composable
fun SnoozeDialogPreview() {
    ru.yotfr.designsystem.theme.AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            SnoozeDialog(
                onDismissRequest = {},
                onConfirmation = {},
                selectedSnooze = ru.yotfr.model.Snooze.FIFTEEN
            )
        }
    }
}

@Composable
fun SnoozeDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (ru.yotfr.model.Snooze) -> Unit,
    selectedSnooze: ru.yotfr.model.Snooze
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background,
                    shape = ru.yotfr.designsystem.theme.AlarmTheme.shape.default
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.snooze),
                style = ru.yotfr.designsystem.theme.AlarmTheme.typography.headline,
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
            )
            Spacer(modifier = Modifier.height(32.dp))
            ru.yotfr.model.Snooze.values().forEach { snooze ->
                SnoozeItem(
                    snooze = snooze,
                    selected = selectedSnooze == snooze,
                    onCLick =  { onConfirmation(snooze) }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SnoozeItem(
    snooze: ru.yotfr.model.Snooze,
    selected: Boolean,
    onCLick: (ru.yotfr.model.Snooze) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onCLick(snooze) }
            .punchedShadow(
                offsetX = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                offsetY = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                blurRadius = ru.yotfr.designsystem.theme.AlarmTheme.shadowBlurRadius.default,
                shape = ru.yotfr.designsystem.component.Shape.RoundedRect,
                darkColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.darkShadow,
                lightColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.lightShadow,
                cornerRadius = ru.yotfr.designsystem.theme.AlarmTheme.shapeCornerRadius.small
            )
            .clip(ru.yotfr.designsystem.theme.AlarmTheme.shape.small)
            .background(
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .pressedShadow(
                    offsetX = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.large,
                    offsetY = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.large,
                    blurRadius = ru.yotfr.designsystem.theme.AlarmTheme.shadowBlurRadius.default,
                    shape = ru.yotfr.designsystem.component.Shape.RoundedRect,
                    darkColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.darkShadow,
                    lightColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.lightShadow,
                    cornerRadius = ru.yotfr.designsystem.theme.AlarmTheme.shapeCornerRadius.extraSmall,
                    backgroundColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
                )
                .clip(ru.yotfr.designsystem.theme.AlarmTheme.shape.extraSmall)
                .background(
                    color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null,
                    tint = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                )
            }
        }
        Text(
            text = snooze.stringResource(),
            style = ru.yotfr.designsystem.theme.AlarmTheme.typography.caption,
            color = if (selected) ru.yotfr.designsystem.theme.AlarmTheme.colors.text else ru.yotfr.designsystem.theme.AlarmTheme.colors.disabledText
        )
    }
}
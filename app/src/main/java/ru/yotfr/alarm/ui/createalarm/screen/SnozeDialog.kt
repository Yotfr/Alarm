package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
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
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.pressedShadow
import ru.yotfr.alarm.ui.common.punchedShadow
import ru.yotfr.alarm.ui.createalarm.mapper.stringResource

@Preview
@Composable
fun SnoozeDialogPreview() {
    AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AlarmTheme.colors.background),
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
                    color = AlarmTheme.colors.background,
                    shape = AlarmTheme.shape.default
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.snooze),
                style = AlarmTheme.typography.headline,
                color = AlarmTheme.colors.text
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
                offsetX = AlarmTheme.shadowOffset.default,
                offsetY = AlarmTheme.shadowOffset.default,
                blurRadius = AlarmTheme.shadowBlurRadius.default,
                shape = Shape.RoundedRect,
                darkColor = AlarmTheme.colors.darkShadow,
                lightColor = AlarmTheme.colors.lightShadow,
                cornerRadius = AlarmTheme.shapeCornerRadius.small
            )
            .clip(AlarmTheme.shape.small)
            .background(
                color = AlarmTheme.colors.background
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .pressedShadow(
                    offsetX = AlarmTheme.shadowOffset.large,
                    offsetY = AlarmTheme.shadowOffset.large,
                    blurRadius = AlarmTheme.shadowBlurRadius.default,
                    shape = Shape.RoundedRect,
                    darkColor = AlarmTheme.colors.darkShadow,
                    lightColor = AlarmTheme.colors.lightShadow,
                    cornerRadius = AlarmTheme.shapeCornerRadius.extraSmall,
                    backgroundColor = AlarmTheme.colors.background
                )
                .clip(AlarmTheme.shape.extraSmall)
                .background(
                    color = AlarmTheme.colors.background
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null,
                    tint = AlarmTheme.colors.text
                )
            }
        }
        Text(
            text = snooze.stringResource(),
            style = AlarmTheme.typography.caption,
            color = if (selected) AlarmTheme.colors.text else AlarmTheme.colors.disabledText
        )
    }
}
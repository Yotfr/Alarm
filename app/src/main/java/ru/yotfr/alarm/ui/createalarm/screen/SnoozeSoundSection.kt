package ru.yotfr.alarm.ui.createalarm.screen

import ru.yotfr.designsystem.theme.AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.R
import ru.yotfr.model.Snooze
import ru.yotfr.model.Sound
import ru.yotfr.designsystem.component.Shape
import ru.yotfr.designsystem.component.pressedShadow
import ru.yotfr.designsystem.component.punchedShadow
import ru.yotfr.alarm.ui.common.stringResource
import ru.yotfr.alarm.ui.createalarm.mapper.stringResource

@Composable
fun SnoozeSoundSection(
    snooze: ru.yotfr.model.Snooze,
    onSnoozeChanged: (ru.yotfr.model.Snooze) -> Unit,
    sound: ru.yotfr.model.Sound,
    onSoundChanged: (ru.yotfr.model.Sound) -> Unit,
    vibrate: Boolean,
    onVibrateChange: (Boolean) -> Unit,
    soundLevel: Float,
    onSoundLevelChanged: (Float) -> Unit
) {
    var isSnoozeDialogOpened by remember { mutableStateOf(false) }
    var isSoundDialogOpened by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .punchedShadow(
                offsetX = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                offsetY = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                blurRadius = ru.yotfr.designsystem.theme.AlarmTheme.shadowBlurRadius.default,
                shape = ru.yotfr.designsystem.component.Shape.RoundedRect,
                darkColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.darkShadow,
                lightColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.lightShadow,
                cornerRadius = ru.yotfr.designsystem.theme.AlarmTheme.shapeCornerRadius.default
            )
            .clip(ru.yotfr.designsystem.theme.AlarmTheme.shape.default)
            .background(
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
            )
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.snooze),
                style = ru.yotfr.designsystem.theme.AlarmTheme.typography.headline,
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        isSnoozeDialogOpened = true
                    }
                    .clip(ru.yotfr.designsystem.theme.AlarmTheme.shape.small)
                    .pressedShadow(
                        offsetX = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                        offsetY = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                        blurRadius = ru.yotfr.designsystem.theme.AlarmTheme.shadowBlurRadius.default,
                        darkColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.darkShadow,
                        lightColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.lightShadow,
                        shape = ru.yotfr.designsystem.component.Shape.RoundedRect,
                        cornerRadius = ru.yotfr.designsystem.theme.AlarmTheme.shapeCornerRadius.small,
                        backgroundColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
                    )
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 8.dp,
                        end = 0.dp
                    )
            ) {
                Text(
                    text = snooze.stringResource(),
                    style = ru.yotfr.designsystem.theme.AlarmTheme.typography.caption,
                    color = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.sound),
                style = ru.yotfr.designsystem.theme.AlarmTheme.typography.headline,
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        isSoundDialogOpened = true
                    }
                    .clip(ru.yotfr.designsystem.theme.AlarmTheme.shape.small)
                    .pressedShadow(
                        offsetX = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                        offsetY = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                        blurRadius = ru.yotfr.designsystem.theme.AlarmTheme.shadowBlurRadius.default,
                        darkColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.darkShadow,
                        lightColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.lightShadow,
                        shape = ru.yotfr.designsystem.component.Shape.RoundedRect,
                        cornerRadius = ru.yotfr.designsystem.theme.AlarmTheme.shapeCornerRadius.small,
                        backgroundColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
                    )
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 8.dp,
                        end = 0.dp
                    )
            ) {
                Text(
                    text = sound.stringResource(),
                    style = ru.yotfr.designsystem.theme.AlarmTheme.typography.caption,
                    color = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                )
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
    }
    if (isSnoozeDialogOpened) {
        SnoozeDialog(
            onDismissRequest = { isSnoozeDialogOpened = false },
            onConfirmation = {
                onSnoozeChanged(it)
                isSnoozeDialogOpened = false
            },
            selectedSnooze = snooze
        )
    }
    if (isSoundDialogOpened) {
        SoundDialog(
            onDismissRequest = { isSoundDialogOpened = false },
            onSoundChanged = onSoundChanged,
            selectedSound = sound,
            vibrate = vibrate,
            onVibrateChanged = onVibrateChange,
            soundLevelPercent = soundLevel,
            onSoundLevelChanged = onSoundLevelChanged
        )
    }
}
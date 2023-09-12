package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
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
import ru.yotfr.alarm.domain.model.Snooze
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.pressedShadow
import ru.yotfr.alarm.ui.common.punchedShadow
import ru.yotfr.alarm.ui.createalarm.mapper.stringResource

@Composable
fun SnoozeSoundSection(
    snooze: Snooze,
    onSnoozeChanged: (Snooze) -> Unit
) {
    var isSnoozeDialogOpened by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .punchedShadow(
                offsetX = AlarmTheme.shadowOffset.default,
                offsetY = AlarmTheme.shadowOffset.default,
                blurRadius = AlarmTheme.shadowBlurRadius.default,
                shape = Shape.RoundedRect,
                darkColor = AlarmTheme.colors.darkShadow,
                lightColor = AlarmTheme.colors.lightShadow,
                cornerRadius = AlarmTheme.shapeCornerRadius.default
            )
            .clip(AlarmTheme.shape.default)
            .background(
                color = AlarmTheme.colors.background
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
                text = stringResource(id = R.string.label),
                style = AlarmTheme.typography.headline,
                color = AlarmTheme.colors.text
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
                    .clip(AlarmTheme.shape.small)
                    .pressedShadow(
                        offsetX = AlarmTheme.shadowOffset.default,
                        offsetY = AlarmTheme.shadowOffset.default,
                        blurRadius = AlarmTheme.shadowBlurRadius.default,
                        darkColor = AlarmTheme.colors.darkShadow,
                        lightColor = AlarmTheme.colors.lightShadow,
                        shape = Shape.RoundedRect,
                        cornerRadius = AlarmTheme.shapeCornerRadius.small,
                        backgroundColor = AlarmTheme.colors.background
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
                    style = AlarmTheme.typography.caption,
                    color = AlarmTheme.colors.text
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = AlarmTheme.colors.text
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
                style = AlarmTheme.typography.headline,
                color = AlarmTheme.colors.text
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                       //TODO
                    }
                    .clip(AlarmTheme.shape.small)
                    .pressedShadow(
                        offsetX = AlarmTheme.shadowOffset.default,
                        offsetY = AlarmTheme.shadowOffset.default,
                        blurRadius = AlarmTheme.shadowBlurRadius.default,
                        darkColor = AlarmTheme.colors.darkShadow,
                        lightColor = AlarmTheme.colors.lightShadow,
                        shape = Shape.RoundedRect,
                        cornerRadius = AlarmTheme.shapeCornerRadius.small,
                        backgroundColor = AlarmTheme.colors.background
                    )
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 8.dp,
                        end = 0.dp
                    )
            ) {
                Text(
                    text = "TODO",
                    style = AlarmTheme.typography.caption,
                    color = AlarmTheme.colors.text
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = AlarmTheme.colors.text
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
}
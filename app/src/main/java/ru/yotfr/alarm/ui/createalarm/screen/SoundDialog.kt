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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.yotfr.alarm.R
import ru.yotfr.model.Sound
import ru.yotfr.mediaplayer.AlarmPlayer
import ru.yotfr.designsystem.component.Shape
import ru.yotfr.designsystem.component.pressedShadow
import ru.yotfr.designsystem.component.punchedShadow
import ru.yotfr.alarm.ui.common.stringResource

@Preview
@Composable
fun SoundDialogPreview() {
    ru.yotfr.designsystem.theme.AlarmTheme {

        var progress by remember { mutableStateOf(0f) }
        var vibrate by remember { mutableStateOf(false) }
        var sound by remember { mutableStateOf(ru.yotfr.model.Sound.FIRST) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            SoundDialog(
                onDismissRequest = { },
                onSoundChanged = { sound = it },
                selectedSound = sound,
                vibrate = vibrate,
                onVibrateChanged = { vibrate = it },
                soundLevelPercent = progress,
                onSoundLevelChanged = { progress = it }
            )
        }
    }
}

@Composable
fun SoundDialog(
    onDismissRequest: () -> Unit,
    onSoundChanged: (ru.yotfr.model.Sound) -> Unit,
    selectedSound: ru.yotfr.model.Sound,
    vibrate:Boolean,
    onVibrateChanged: (Boolean) -> Unit,
    soundLevelPercent: Float,
    onSoundLevelChanged: (Float) -> Unit,
) {
    val context = LocalContext.current
    var alarmPlayer by remember { mutableStateOf<ru.yotfr.mediaplayer.AlarmPlayer?>(null) }

    DisposableEffect(Unit) {
        alarmPlayer = ru.yotfr.mediaplayer.AlarmPlayer(
            isLooping = false,
            context = context,
            soundLevel = soundLevelPercent
        )
        onDispose {
            alarmPlayer?.destroyAlarmPlayer()
            alarmPlayer = null
        }
    }


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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.snooze),
                    style = ru.yotfr.designsystem.theme.AlarmTheme.typography.headline,
                    color = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                )
                IconButton(
                    onClick = onDismissRequest
                ) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = null,
                        tint = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            LazyColumn {
                items(ru.yotfr.model.Sound.values()) { sound ->
                    SoundItem(
                        sound = sound,
                        selected = selectedSound == sound,
                        onCLick =  {
                            alarmPlayer?.playSound(it)
                            onSoundChanged(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            SoundLevelRow(
                vibrate = vibrate,
                onVibrateChanged = onVibrateChanged,
                soundLevelPercent = soundLevelPercent,
                onSoundLevelChanged = {
                    alarmPlayer?.setVolume(it)
                    onSoundLevelChanged(it)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SoundLevelRow(
    vibrate:Boolean,
    onVibrateChanged: (Boolean) -> Unit,
    soundLevelPercent: Float,
    onSoundLevelChanged: (Float) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(4f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_volume),
                contentDescription = null,
                tint = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
            )
            Spacer(modifier = Modifier.width(4.dp))
            Slider(
                value = soundLevelPercent,
                onValueChange = {
                    if (it >= 0.1f) {
                        onSoundLevelChanged(it)
                    }
                },
                valueRange = 0f..1f,
                steps = 10,
                colors = SliderDefaults.colors(
                    activeTrackColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.text,
                    activeTickColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.text,
                    thumbColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.text,
                    inactiveTickColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                )
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_vibration),
                contentDescription = null,
                tint = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onVibrateChanged(!vibrate) }
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
                if (vibrate) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = null,
                        tint = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                    )
                }
            }
        }
    }
}

@Composable
fun SoundItem(
    sound: ru.yotfr.model.Sound,
    selected: Boolean,
    onCLick: (ru.yotfr.model.Sound) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onCLick(sound) }
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
            text = sound.stringResource(),
            style = ru.yotfr.designsystem.theme.AlarmTheme.typography.caption,
            color = if (selected) ru.yotfr.designsystem.theme.AlarmTheme.colors.text else ru.yotfr.designsystem.theme.AlarmTheme.colors.disabledText
        )
    }
}
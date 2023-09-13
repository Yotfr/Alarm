package ru.yotfr.alarm.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.yotfr.alarm.R
import ru.yotfr.alarm.domain.model.Sound

@Composable
fun Sound.stringResource(): String {
    return when(this) {
        Sound.FIRST -> stringResource(R.string.first_sound)
        Sound.SECOND -> stringResource(R.string.second_sound)
        Sound.THIRD -> stringResource(R.string.third_sound)
    }
}
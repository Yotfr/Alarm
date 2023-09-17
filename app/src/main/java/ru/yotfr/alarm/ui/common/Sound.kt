package ru.yotfr.alarm.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.yotfr.alarm.R
import ru.yotfr.model.Sound

@Composable
fun ru.yotfr.model.Sound.stringResource(): String {
    return when(this) {
        ru.yotfr.model.Sound.FIRST -> stringResource(R.string.first_sound)
        ru.yotfr.model.Sound.SECOND -> stringResource(R.string.second_sound)
        ru.yotfr.model.Sound.THIRD -> stringResource(R.string.third_sound)
    }
}
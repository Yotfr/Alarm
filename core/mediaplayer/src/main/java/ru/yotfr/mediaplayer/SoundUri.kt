package ru.yotfr.mediaplayer

import android.content.Context
import android.net.Uri
import ru.yotfr.alarm.R
import ru.yotfr.model.Sound

fun ru.yotfr.model.Sound.uri(context: Context): Uri {
    return Uri.parse(
        "android.resource://" + context.packageName + "/" + when(this) {
            ru.yotfr.model.Sound.FIRST -> R.raw.first_sound
            ru.yotfr.model.Sound.SECOND -> R.raw.second_sound
            ru.yotfr.model.Sound.THIRD -> R.raw.third_sound
        }
    )
}
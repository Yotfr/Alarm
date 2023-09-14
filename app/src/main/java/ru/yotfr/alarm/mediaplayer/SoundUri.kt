package ru.yotfr.alarm.mediaplayer

import android.content.Context
import android.net.Uri
import ru.yotfr.alarm.R
import ru.yotfr.alarm.domain.model.Sound

fun Sound.uri(context: Context): Uri {
    return Uri.parse(
        "android.resource://" + context.packageName + "/" + when(this) {
            Sound.FIRST -> R.raw.first_sound
            Sound.SECOND -> R.raw.second_sound
            Sound.THIRD -> R.raw.third_sound
        }
    )
}
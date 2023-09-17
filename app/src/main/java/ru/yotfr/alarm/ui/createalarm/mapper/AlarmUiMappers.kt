package ru.yotfr.alarm.ui.createalarm.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.yotfr.alarm.R
import ru.yotfr.model.Snooze
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
fun LocalDateTime.remainTime(): String {
    val nowTime = LocalDateTime.now()
    if (this.isBefore(nowTime)) return ""
    val remainTimeDays = nowTime.until(this, ChronoUnit.DAYS)
    if (remainTimeDays > 0L) {
        return if (remainTimeDays == 1L) {
            stringResource(id = R.string.ring_in) + " " + remainTimeDays.toString() + " " + stringResource(id = R.string.day)
        } else stringResource(id = R.string.ring_in) + " " + remainTimeDays.toString() + " " + stringResource(id = R.string.days)
    }
    val remainTimeHours = nowTime.until(this, ChronoUnit.HOURS)
    if (remainTimeHours > 0L) {
        val nowTimeWithoutHours = nowTime.plusHours(remainTimeHours)
        val remainTimeMinutes = nowTimeWithoutHours.until(this, ChronoUnit.MINUTES)
        val remainTimeHoursString = if (remainTimeHours == 1L) {
            remainTimeHours.toString() + " " + stringResource(id = R.string.hour)
        } else remainTimeHours.toString() + " " + stringResource(id = R.string.hours)
        if (remainTimeMinutes < 1L) {
            return stringResource(id = R.string.ring_in) + " " + remainTimeHoursString
        }
        val minuteTimeString = if (remainTimeMinutes == 1L) {
            remainTimeMinutes.toString() + " " + stringResource(id = R.string.minute)
        } else remainTimeMinutes.toString() + " " + stringResource(id = R.string.minutes)
        return stringResource(id = R.string.ring_in) + " " + remainTimeHoursString + " " + "and" + " " + minuteTimeString
    }
    val remainTimeMinutes = nowTime.until(this, ChronoUnit.MINUTES)
    if (remainTimeMinutes < 1L) {
        return stringResource(id = R.string.ring_in) + " " + stringResource(id = R.string.less_than)
    }
    return if (remainTimeMinutes == 1L) {
        stringResource(id = R.string.ring_in) + " " + remainTimeMinutes.toString() + " " + stringResource(id = R.string.minute)
    } else  stringResource(id = R.string.ring_in) + " " + remainTimeMinutes.toString() + " " + stringResource(id = R.string.minutes)
}

@Composable
fun ru.yotfr.model.Snooze.stringResource(): String {
    return when(this) {
        ru.yotfr.model.Snooze.FIVE -> stringResource(R.string.five)
        ru.yotfr.model.Snooze.TEN -> stringResource(R.string.ten)
        ru.yotfr.model.Snooze.FIFTEEN -> stringResource(R.string.fifteen)
        ru.yotfr.model.Snooze.TWENTY -> stringResource(R.string.twenty)
        ru.yotfr.model.Snooze.TWENTY_FIVE -> stringResource(R.string.twenty_five)
        ru.yotfr.model.Snooze.THIRTY -> stringResource(R.string.thirty)
        ru.yotfr.model.Snooze.OFF -> stringResource(R.string.snooze_off)
    }
}
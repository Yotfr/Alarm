package ru.yotfr.alarm.ui.createalarm.event

import ru.yotfr.model.Snooze
import ru.yotfr.model.Sound
import java.time.DayOfWeek
import java.time.LocalTime

sealed interface CreateAlarmEvent {
    data class EnterScreen(val id: Long?) : CreateAlarmEvent
    data class OnWeekDayClicked(val weekDay: DayOfWeek) : CreateAlarmEvent
    data class OnTriggerTimeChanged(val localTime: LocalTime) : CreateAlarmEvent
    data class OnLabelChanged(val newLabel: String) : CreateAlarmEvent
    data class OnSnoozeChanged(val snooze: ru.yotfr.model.Snooze) : CreateAlarmEvent
    data object WeekDaysCleared : CreateAlarmEvent
    data object SaveClicked : CreateAlarmEvent
    data class OnVibrateChanged(val newVibrate: Boolean) : CreateAlarmEvent
    data class OnSoundChanged(val newSound: ru.yotfr.model.Sound) : CreateAlarmEvent
    data class SoundLevelChanged(val newSoundLevel: Float) : CreateAlarmEvent
}
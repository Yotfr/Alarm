package ru.yotfr.alarm.ui.createalarm.event

import ru.yotfr.alarm.domain.model.Snooze
import ru.yotfr.alarm.domain.model.WeekDays
import java.time.LocalTime

sealed interface CreateAlarmEvent {
    data class EnterScreen(val id: Long?) : CreateAlarmEvent
    data class OnWeekDayClicked(val weekDay: WeekDays) : CreateAlarmEvent
    data class OnTriggerTimeChanged(val localTime: LocalTime) : CreateAlarmEvent
    data class OnLabelChanged(val newLabel: String) : CreateAlarmEvent
    data class OnSnoozeChanged(val snooze: Snooze) : CreateAlarmEvent
    object WeekDaysCleared : CreateAlarmEvent
    object SaveClicked : CreateAlarmEvent
}
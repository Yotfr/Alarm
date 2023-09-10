package ru.yotfr.alarm.ui.createalarm.event

import ru.yotfr.alarm.domain.model.WeekDays
import java.time.LocalTime

sealed interface CreateAlarmEvent {
    data class EnterScreen(val id: Long?) : CreateAlarmEvent
    data class OnWeekDayClicked(val weekDay: WeekDays) : CreateAlarmEvent
    data class OnTriggerTimeChanged(val localTime: LocalTime) : CreateAlarmEvent
    object SaveClicked : CreateAlarmEvent
}
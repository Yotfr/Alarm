package ru.yotfr.alarmlist.event

import ru.yotfr.model.Alarm

sealed interface AlarmListEvent {
    data class ToggleAlarm(val alarmModel: Alarm, val newValue: Boolean) :
        AlarmListEvent
    data class DeleteAlarm(val alarmModel: Alarm) : AlarmListEvent
}
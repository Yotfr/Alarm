package ru.yotfr.alarm.ui.alarmlist.event

import ru.yotfr.alarm.domain.model.AlarmModel

sealed interface AlarmListEvent {
    data class ToggleAlarm(val alarmModel: AlarmModel, val newValue: Boolean) : AlarmListEvent
    data class DeleteAlarm(val alarmModel: AlarmModel) : AlarmListEvent
}
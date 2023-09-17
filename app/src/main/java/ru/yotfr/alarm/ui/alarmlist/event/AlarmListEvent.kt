package ru.yotfr.alarm.ui.alarmlist.event

import ru.yotfr.model.AlarmModel

sealed interface AlarmListEvent {
    data class ToggleAlarm(val alarmModel: ru.yotfr.model.AlarmModel, val newValue: Boolean) : AlarmListEvent
    data class DeleteAlarm(val alarmModel: ru.yotfr.model.AlarmModel) : AlarmListEvent
}
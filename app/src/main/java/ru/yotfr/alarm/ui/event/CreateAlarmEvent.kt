package ru.yotfr.alarm.ui.event

sealed interface CreateAlarmEvent {
    object NavigateBack : CreateAlarmEvent
}
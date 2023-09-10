package ru.yotfr.alarm.ui.createalarm.event

sealed interface CreateAlarmScreenEvent {
    object NavigateBack : CreateAlarmScreenEvent
}
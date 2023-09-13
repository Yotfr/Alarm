package ru.yotfr.alarm.ui.createalarm.event

sealed interface CreateAlarmScreenEvent {
    data object NavigateBack : CreateAlarmScreenEvent
}
package ru.yotfr.alarm.ui.alarmring.event

sealed interface AlarmRingScreenEvent {
    data object NavigateBack: AlarmRingScreenEvent
}
package ru.yotfr.alarm.ui.alarmring.event

sealed interface AlarmRingEvent {
    data class EnterScreen(val id: Long) : AlarmRingEvent
    data object DismissAlarm : AlarmRingEvent
    data object SnoozeAlarm : AlarmRingEvent
}
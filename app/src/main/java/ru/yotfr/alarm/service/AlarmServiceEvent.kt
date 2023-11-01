package ru.yotfr.alarm.service

sealed interface AlarmServiceEvent {
    data object AlarmDismissed : AlarmServiceEvent
    data object AlarmSnoozed : AlarmServiceEvent
}

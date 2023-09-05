package ru.yotfr.alarm.ui.state

import ru.yotfr.alarm.domain.AlarmModel

data class AlarmsListScreenState(
    val alarms: List<AlarmModel> = emptyList()
)
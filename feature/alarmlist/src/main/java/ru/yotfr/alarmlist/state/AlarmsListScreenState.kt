package ru.yotfr.alarmlist.state

import ru.yotfr.model.Alarm

data class AlarmsListScreenState(
    val alarms: List<Alarm> = emptyList()
)
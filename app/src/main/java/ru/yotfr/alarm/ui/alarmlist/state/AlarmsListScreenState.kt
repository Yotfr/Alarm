package ru.yotfr.alarm.ui.alarmlist.state

import ru.yotfr.model.AlarmModel

data class AlarmsListScreenState(
    val alarms: List<ru.yotfr.model.AlarmModel> = emptyList()
)
package ru.yotfr.alarm.ui.alarmlist.state

import ru.yotfr.alarm.domain.model.AlarmModel

data class AlarmsListScreenState(
    val alarms: List<AlarmModel> = emptyList()
)
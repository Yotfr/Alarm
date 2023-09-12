package ru.yotfr.alarm.domain.model

import java.time.LocalDateTime

data class AlarmModel(
    val id: Long = 0,
    val triggerTime: LocalDateTime = LocalDateTime.now(),
    val isActive: Boolean = true,
    val activeDays: List<WeekDays> = emptyList(),
    val label: String = "",
    val snooze: Snooze = Snooze.OFF
)
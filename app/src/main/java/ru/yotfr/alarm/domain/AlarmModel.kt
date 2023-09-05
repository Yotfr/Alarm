package ru.yotfr.alarm.domain

import java.time.LocalDateTime

data class AlarmModel(
    val id: Long,
    val triggerTime: LocalDateTime,
    val isActive: Boolean
)
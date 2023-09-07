package ru.yotfr.alarm.domain

import java.time.LocalDateTime

interface AlarmScheduler {
    fun scheduleAlarm(triggerTime: LocalDateTime, id: Long)
}
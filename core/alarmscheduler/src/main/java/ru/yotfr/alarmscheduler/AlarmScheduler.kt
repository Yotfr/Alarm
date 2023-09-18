package ru.yotfr.alarmscheduler

import java.time.LocalDateTime

interface AlarmScheduler {
    fun scheduleAlarm(triggerTime: LocalDateTime, id: Long)
    fun dismissAlarm()
    suspend fun cancelAlarm(id: Long)
}
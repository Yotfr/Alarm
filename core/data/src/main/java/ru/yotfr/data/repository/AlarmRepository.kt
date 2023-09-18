package ru.yotfr.data.repository

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.Alarm

interface AlarmRepository {
    fun getAllAlarms(): Flow<List<Alarm>>
    suspend fun insertAlarm(alarmModel: Alarm) : Long
    suspend fun deleteAlarm(alarmModel: Alarm)
    suspend fun updateAlarm(alarmModel: Alarm)
    suspend fun getAlarmById(id: Long): Alarm
}
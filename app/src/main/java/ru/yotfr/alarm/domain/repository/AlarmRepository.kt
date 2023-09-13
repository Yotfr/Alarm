package ru.yotfr.alarm.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.yotfr.alarm.domain.model.AlarmModel

interface AlarmRepository {
    fun getAllAlarms(): Flow<List<AlarmModel>>
    suspend fun insertAlarm(alarmModel: AlarmModel) : Long
    suspend fun deleteAlarm(alarmModel: AlarmModel)
    suspend fun updateAlarm(alarmModel: AlarmModel)
    suspend fun getAlarmById(id: Long): AlarmModel
}
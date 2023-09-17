package ru.yotfr.alarm.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.AlarmModel

interface AlarmRepository {
    fun getAllAlarms(): Flow<List<ru.yotfr.model.AlarmModel>>
    suspend fun insertAlarm(alarmModel: ru.yotfr.model.AlarmModel) : Long
    suspend fun deleteAlarm(alarmModel: ru.yotfr.model.AlarmModel)
    suspend fun updateAlarm(alarmModel: ru.yotfr.model.AlarmModel)
    suspend fun getAlarmById(id: Long): ru.yotfr.model.AlarmModel
}
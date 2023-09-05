package ru.yotfr.alarm.domain

import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    fun getAllAlarms(): Flow<List<AlarmModel>>
    suspend fun insertAlarm(alarmModel: AlarmModel)

}
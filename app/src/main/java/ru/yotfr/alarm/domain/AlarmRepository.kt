package ru.yotfr.alarm.domain

import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    fun getAllAlarms(): Flow<List<AlarmModel>>
    suspend fun insertAlarm(alarmModel: AlarmModel) : Long
    suspend fun cancelAlarm(alarmModel: AlarmModel)
    suspend fun activateAlarm(alarmModel: AlarmModel)

}
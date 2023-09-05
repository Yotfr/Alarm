package ru.yotfr.alarm.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.yotfr.alarm.domain.AlarmModel
import ru.yotfr.alarm.domain.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmDao: AlarmDao
) : AlarmRepository {
    override fun getAllAlarms(): Flow<List<AlarmModel>> {
        return alarmDao.getAllAlarms().map { it.map { it.map() } }
    }

    override suspend fun insertAlarm(alarmModel: AlarmModel) {
        alarmDao.insertAlarm(alarmModel.map())
    }
}
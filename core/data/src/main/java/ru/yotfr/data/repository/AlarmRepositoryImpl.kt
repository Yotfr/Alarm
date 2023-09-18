package ru.yotfr.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.yotfr.data.util.mapAsEntity
import ru.yotfr.data.util.mapAsModel
import ru.yotfr.database.dao.AlarmDao
import ru.yotfr.model.Alarm
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmDao: AlarmDao
) : AlarmRepository {

    override fun getAllAlarms(): Flow<List<Alarm>> {
        return alarmDao.getAllAlarms().map { it.map { it.mapAsModel() } }
    }

    override suspend fun insertAlarm(alarmModel: Alarm): Long {
        return alarmDao.insertAlarm(alarmModel.mapAsEntity())
    }

    override suspend fun deleteAlarm(alarmModel: Alarm) {
        alarmDao.deleteAlarm(alarmModel.mapAsEntity())
    }

    override suspend fun updateAlarm(alarmModel: Alarm) {
        alarmDao.updateAlarm(alarmModel.mapAsEntity())
    }

    override suspend fun getAlarmById(id: Long): Alarm {
        return alarmDao.getById(id).mapAsModel()
    }
}
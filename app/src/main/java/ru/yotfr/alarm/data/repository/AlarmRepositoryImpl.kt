package ru.yotfr.alarm.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.yotfr.database.dao.AlarmDao
import ru.yotfr.alarm.data.mappers.map
import ru.yotfr.model.AlarmModel
import ru.yotfr.alarm.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmDao: ru.yotfr.database.dao.AlarmDao
) : AlarmRepository {
    override fun getAllAlarms(): Flow<List<ru.yotfr.model.AlarmModel>> {
        return alarmDao.getAllAlarms().map { it.map { it.map() } }
    }

    override suspend fun insertAlarm(alarmModel: ru.yotfr.model.AlarmModel): Long {
        return alarmDao.insertAlarm(alarmModel.map())
    }

    override suspend fun deleteAlarm(alarmModel: ru.yotfr.model.AlarmModel) {
        alarmDao.deleteAlarm(alarmModel.map())
    }

    override suspend fun updateAlarm(alarmModel: ru.yotfr.model.AlarmModel) {
        alarmDao.updateAlarm(alarmModel.map())
    }

    override suspend fun getAlarmById(id: Long): ru.yotfr.model.AlarmModel {
        return alarmDao.getById(id).map()
    }
}
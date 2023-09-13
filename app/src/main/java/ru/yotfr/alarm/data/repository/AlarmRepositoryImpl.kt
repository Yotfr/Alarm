package ru.yotfr.alarm.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.yotfr.alarm.data.datasource.AlarmDao
import ru.yotfr.alarm.data.mappers.map
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmDao: AlarmDao
) : AlarmRepository {
    override fun getAllAlarms(): Flow<List<AlarmModel>> {
        return alarmDao.getAllAlarms().map { it.map { it.map() } }
    }

    override suspend fun insertAlarm(alarmModel: AlarmModel): Long {
        return alarmDao.insertAlarm(alarmModel.map())
    }

    override suspend fun deleteAlarm(alarmModel: AlarmModel) {
        alarmDao.deleteAlarm(alarmModel.map())
    }

    override suspend fun updateAlarm(alarmModel: AlarmModel) {
        alarmDao.updateAlarm(alarmModel.map())
    }

    override suspend fun getAlarmById(id: Long): AlarmModel {
        return alarmDao.getById(id).map()
    }
}
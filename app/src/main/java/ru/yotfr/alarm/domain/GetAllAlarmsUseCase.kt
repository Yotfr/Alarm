package ru.yotfr.alarm.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAlarmsUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    operator fun invoke(): Flow<List<AlarmModel>> {
        return alarmRepository.getAllAlarms()
    }
}
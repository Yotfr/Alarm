package ru.yotfr.alarm.domain

import javax.inject.Inject

class GetAlarmByIdUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke(id: Long): AlarmModel {
        return alarmRepository.getAlarmById(id)
    }
}
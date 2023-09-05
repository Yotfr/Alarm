package ru.yotfr.alarm.domain

import javax.inject.Inject

class CreateNewAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke(alarmModel: AlarmModel) {
        alarmRepository.insertAlarm(alarmModel)
    }
}
package ru.yotfr.alarm.domain

import javax.inject.Inject

class DeleteAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: AlarmModel) {
        alarmRepository.deleteAlarm(alarmModel)
        alarmScheduler.cancelAlarm(alarmModel.id)
    }
}
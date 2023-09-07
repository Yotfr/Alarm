package ru.yotfr.alarm.domain

import javax.inject.Inject

class CreateNewAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: AlarmModel) {
        val newAlarmId = alarmRepository.insertAlarm(alarmModel)
        alarmScheduler.scheduleAlarm(alarmModel.triggerTime, newAlarmId)
    }
}
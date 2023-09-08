package ru.yotfr.alarm.domain

import javax.inject.Inject

class ChangeAlarmTriggerTimeUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: AlarmModel) {
        alarmRepository.updateAlarm(alarmModel)
        alarmScheduler.cancelAlarm(alarmModel.id)
        alarmScheduler.scheduleAlarm(alarmModel.triggerTime, alarmModel.id)
    }
}
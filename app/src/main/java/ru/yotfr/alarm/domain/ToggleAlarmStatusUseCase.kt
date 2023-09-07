package ru.yotfr.alarm.domain

import javax.inject.Inject

class ToggleAlarmStatusUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: AlarmModel, value: Boolean) {
        if (value) {
            alarmRepository.cancelAlarm(alarmModel)
            alarmScheduler.cancelAlarm(alarmModel.id)
        } else {
            alarmRepository.activateAlarm(alarmModel)
            alarmScheduler.scheduleAlarm(alarmModel.triggerTime, alarmModel.id)
        }
    }
}
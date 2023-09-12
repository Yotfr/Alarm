package ru.yotfr.alarm.domain.usecase

import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.repository.AlarmRepository
import ru.yotfr.alarm.domain.repository.AlarmScheduler
import javax.inject.Inject

class ToggleAlarmStatusUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: AlarmModel, value: Boolean) {
        if (value) {
            alarmRepository.activateAlarm(alarmModel)
            alarmScheduler.scheduleAlarm(alarmModel.triggerTime, alarmModel.id)
        } else {
            alarmRepository.cancelAlarm(alarmModel)
            alarmScheduler.cancelAlarm(alarmModel.id)
        }
    }
}
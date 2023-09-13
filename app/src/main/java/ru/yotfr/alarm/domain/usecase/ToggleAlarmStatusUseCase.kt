package ru.yotfr.alarm.domain.usecase

import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.model.validateTriggerTime
import ru.yotfr.alarm.domain.repository.AlarmRepository
import ru.yotfr.alarm.domain.repository.AlarmScheduler
import javax.inject.Inject

class ToggleAlarmStatusUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: AlarmModel, newState: Boolean) {
        alarmRepository.updateAlarm(
            alarmModel.validateTriggerTime().copy(
                isActive = newState
            )
        )
        if (newState) {
            alarmScheduler.scheduleAlarm(alarmModel.triggerTime, alarmModel.id)
        } else {
            alarmScheduler.cancelAlarm(alarmModel.id)
        }
    }
}
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
        val validatedAlarmModel = alarmModel.validateTriggerTime()
        alarmRepository.updateAlarm(
            validatedAlarmModel.copy(
                isActive = newState
            )
        )
        if (newState) {
            alarmScheduler.scheduleAlarm(validatedAlarmModel.triggerTime, validatedAlarmModel.id)
        } else {
            alarmScheduler.cancelAlarm(validatedAlarmModel.id)
        }
    }
}
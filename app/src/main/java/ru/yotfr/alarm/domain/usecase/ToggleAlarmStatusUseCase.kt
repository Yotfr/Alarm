package ru.yotfr.alarm.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.model.validateTriggerTime
import ru.yotfr.alarm.domain.repository.AlarmRepository
import ru.yotfr.alarm.domain.repository.AlarmScheduler
import javax.inject.Inject

class ToggleAlarmStatusUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: AlarmModel, newState: Boolean) = withContext(Dispatchers.IO) {
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
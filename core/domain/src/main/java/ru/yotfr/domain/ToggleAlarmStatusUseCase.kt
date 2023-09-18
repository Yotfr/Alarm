package ru.yotfr.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.alarmscheduler.AlarmScheduler
import ru.yotfr.data.repository.AlarmRepository
import ru.yotfr.model.Alarm
import ru.yotfr.model.validateTriggerTime
import javax.inject.Inject

class ToggleAlarmStatusUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: Alarm, newState: Boolean) = withContext(Dispatchers.IO) {
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
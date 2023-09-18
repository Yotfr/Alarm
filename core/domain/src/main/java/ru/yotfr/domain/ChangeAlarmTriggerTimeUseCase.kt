package ru.yotfr.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.alarmscheduler.AlarmScheduler
import ru.yotfr.data.repository.AlarmRepository
import ru.yotfr.model.Alarm
import javax.inject.Inject

class ChangeAlarmTriggerTimeUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: Alarm) = withContext(Dispatchers.IO) {
        alarmRepository.updateAlarm(alarmModel)
        alarmScheduler.scheduleAlarm(alarmModel.triggerTime, alarmModel.id)
    }
}
package ru.yotfr.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.alarmscheduler.AlarmScheduler
import ru.yotfr.data.repository.AlarmRepository
import ru.yotfr.model.Alarm
import javax.inject.Inject

class CreateNewAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: Alarm) = withContext(Dispatchers.IO) {
        val newAlarmId = alarmRepository.insertAlarm(alarmModel)
        alarmScheduler.scheduleAlarm(alarmModel.triggerTime, newAlarmId)
    }
}
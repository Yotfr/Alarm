package ru.yotfr.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.alarmscheduler.AlarmScheduler
import ru.yotfr.data.repository.AlarmRepository
import ru.yotfr.model.Alarm
import javax.inject.Inject

class DeleteAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: Alarm) = withContext(Dispatchers.IO) {
        alarmRepository.deleteAlarm(alarmModel)
        alarmScheduler.cancelAlarm(alarmModel.id)
    }
}
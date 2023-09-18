package ru.yotfr.alarm.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.model.AlarmModel
import ru.yotfr.alarmscheduler.AlarmScheduler
import javax.inject.Inject

class DeleteAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: ru.yotfr.model.AlarmModel) = withContext(Dispatchers.IO) {
        alarmRepository.deleteAlarm(alarmModel)
        alarmScheduler.cancelAlarm(alarmModel.id)
    }
}
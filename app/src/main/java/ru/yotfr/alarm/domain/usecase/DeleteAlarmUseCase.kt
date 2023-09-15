package ru.yotfr.alarm.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.repository.AlarmRepository
import ru.yotfr.alarm.domain.repository.AlarmScheduler
import javax.inject.Inject

class DeleteAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(alarmModel: AlarmModel) = withContext(Dispatchers.IO) {
        alarmRepository.deleteAlarm(alarmModel)
        alarmScheduler.cancelAlarm(alarmModel.id)
    }
}
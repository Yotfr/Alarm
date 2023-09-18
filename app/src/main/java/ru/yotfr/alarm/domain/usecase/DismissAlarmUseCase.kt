package ru.yotfr.alarm.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.model.AlarmModel
import ru.yotfr.alarm.domain.repository.AlarmScheduler
import javax.inject.Inject

class DismissAlarmUseCase @Inject constructor(
    private val alarmScheduler: AlarmScheduler,
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke(alarmModel: ru.yotfr.model.AlarmModel) = withContext(Dispatchers.IO) {
        if (alarmModel.activeDays.isEmpty()) {
            alarmRepository.updateAlarm(
                alarmModel.copy(
                    isActive = false
                )
            )
        }
        alarmScheduler.dismissAlarm()
    }
}
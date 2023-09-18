package ru.yotfr.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.alarmscheduler.AlarmScheduler
import ru.yotfr.data.repository.AlarmRepository
import ru.yotfr.model.Alarm
import javax.inject.Inject

class DismissAlarmUseCase @Inject constructor(
    private val alarmScheduler: AlarmScheduler,
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke(alarmModel: Alarm) = withContext(Dispatchers.IO) {
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
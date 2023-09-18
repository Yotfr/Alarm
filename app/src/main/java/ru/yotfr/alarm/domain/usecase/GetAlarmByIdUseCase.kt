package ru.yotfr.alarm.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.model.AlarmModel
import javax.inject.Inject

class GetAlarmByIdUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke(id: Long): ru.yotfr.model.AlarmModel = withContext(Dispatchers.IO) {
        alarmRepository.getAlarmById(id)
    }
}
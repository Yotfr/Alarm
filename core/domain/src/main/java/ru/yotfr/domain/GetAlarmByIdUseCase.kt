package ru.yotfr.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.data.repository.AlarmRepository
import ru.yotfr.model.Alarm
import javax.inject.Inject

class GetAlarmByIdUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke(id: Long): Alarm = withContext(Dispatchers.IO) {
        alarmRepository.getAlarmById(id)
    }
}
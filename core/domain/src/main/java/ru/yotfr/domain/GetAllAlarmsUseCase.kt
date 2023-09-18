package ru.yotfr.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.data.repository.AlarmRepository
import ru.yotfr.model.Alarm
import javax.inject.Inject

class GetAllAlarmsUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    operator fun invoke(): Flow<List<Alarm>> {
        return alarmRepository.getAllAlarms().flowOn(Dispatchers.IO)
    }
}
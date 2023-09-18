package ru.yotfr.alarm.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.model.AlarmModel
import javax.inject.Inject

class GetAllAlarmsUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    operator fun invoke(): Flow<List<ru.yotfr.model.AlarmModel>> {
        return alarmRepository.getAllAlarms().flowOn(Dispatchers.IO)
    }
}
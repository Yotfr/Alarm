package ru.yotfr.alarm.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.repository.AlarmRepository
import javax.inject.Inject

class GetAllAlarmsUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    operator fun invoke(): Flow<List<AlarmModel>> {
        return alarmRepository.getAllAlarms()
    }
}
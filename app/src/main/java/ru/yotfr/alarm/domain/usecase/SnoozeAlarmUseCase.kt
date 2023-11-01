package ru.yotfr.alarm.domain.usecase

import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.repository.AlarmScheduler
import javax.inject.Inject

class SnoozeAlarmUseCase @Inject constructor(
    private val alarmScheduler: AlarmScheduler
) {
    operator fun invoke(alarmModel: AlarmModel, snoozeMinutes: Int) {
        alarmScheduler.scheduleAlarm(
            alarmModel.triggerTime.plusMinutes(snoozeMinutes.toLong()),
            id = alarmModel.id
        )
    }
}
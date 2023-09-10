package ru.yotfr.alarm.data.mappers

import ru.yotfr.alarm.data.datasource.AlarmEntity
import ru.yotfr.alarm.domain.model.AlarmModel

fun AlarmEntity.map(): AlarmModel {
    return AlarmModel(
        id = id,
        triggerTime = triggerTime,
        isActive = isActive,
        activeDays = activeWeekDays
    )
}

fun AlarmModel.map(): AlarmEntity {
    return AlarmEntity(
        id = id,
        triggerTime = triggerTime,
        isActive = isActive,
        activeWeekDays = activeDays
    )
}
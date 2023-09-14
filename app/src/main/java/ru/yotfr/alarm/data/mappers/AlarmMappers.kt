package ru.yotfr.alarm.data.mappers

import ru.yotfr.alarm.data.datasource.AlarmEntity
import ru.yotfr.alarm.domain.model.AlarmModel

fun AlarmEntity.map(): AlarmModel {
    return AlarmModel(
        id = id,
        triggerTime = triggerTime,
        isActive = isActive,
        activeDays = activeWeekDays,
        label = label,
        snooze = snooze,
        sound = sound,
        vibrate = vibrate,
        volume = volume
    )
}

fun AlarmModel.map(): AlarmEntity {
    return AlarmEntity(
        id = id,
        triggerTime = triggerTime,
        isActive = isActive,
        activeWeekDays = activeDays,
        label = label,
        snooze = snooze,
        sound = sound,
        vibrate = vibrate,
        volume = volume
    )
}
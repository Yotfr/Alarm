package ru.yotfr.alarm.data.mappers

import ru.yotfr.database.model.AlarmEntity
import ru.yotfr.model.AlarmModel

fun ru.yotfr.database.model.AlarmEntity.map(): ru.yotfr.model.AlarmModel {
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

fun ru.yotfr.model.AlarmModel.map(): ru.yotfr.database.model.AlarmEntity {
    return ru.yotfr.database.model.AlarmEntity(
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
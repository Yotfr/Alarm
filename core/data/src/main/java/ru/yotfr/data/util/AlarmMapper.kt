package ru.yotfr.data.util

import ru.yotfr.database.model.AlarmEntity
import ru.yotfr.model.Alarm

fun Alarm.mapAsEntity(): AlarmEntity {
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

fun AlarmEntity.mapAsModel(): Alarm {
    return Alarm(
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
package ru.yotfr.alarm.data

import ru.yotfr.alarm.domain.AlarmModel

fun AlarmEntity.map(): AlarmModel {
    return AlarmModel(
        id = id,
        triggerTime = triggerTime,
        isActive = isActive
    )
}

fun AlarmModel.map(): AlarmEntity {
    return AlarmEntity(
        id = id,
        triggerTime = triggerTime,
        isActive = isActive
    )
}
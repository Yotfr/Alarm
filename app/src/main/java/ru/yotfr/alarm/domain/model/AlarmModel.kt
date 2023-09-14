package ru.yotfr.alarm.domain.model

import java.time.DayOfWeek
import java.time.LocalDateTime

data class AlarmModel(
    val id: Long = 0,
    val triggerTime: LocalDateTime = LocalDateTime.now(),
    val isActive: Boolean = true,
    val activeDays: List<DayOfWeek> = emptyList(),
    val label: String = "",
    val snooze: Snooze = Snooze.OFF,
    val sound: Sound = Sound.FIRST,
    val vibrate: Boolean = false,
    val volume: Float = 1f
)

/**
 * Check if the trigger date is after current date, if not, finds correct trigger time
 * and return a copy of alarm
 */
fun AlarmModel.validateTriggerTime(): AlarmModel {
    val currentDateTime = LocalDateTime.now()
    if (triggerTime.isAfter(currentDateTime)) return this
    if (activeDays.isEmpty()) return copy(triggerTime = triggerTime.plusDays(1))
    val closestActiveDay = activeDays.findClosestActiveDay(false)
    var newTriggerTime = triggerTime
    do {
        newTriggerTime = newTriggerTime.plusDays(1)
    } while (newTriggerTime.dayOfWeek != closestActiveDay)
    return copy(triggerTime = newTriggerTime)
}

/**
 * Returns next trigger time, or null if alarm one-off
 */
fun AlarmModel.getNextTriggerTime(): LocalDateTime? {
    if (activeDays.isEmpty()) return null
    val closestActiveDay = activeDays.findClosestActiveDay(false)
    var newTriggerTime = triggerTime
    do {
        newTriggerTime = newTriggerTime.plusDays(1)
    } while (newTriggerTime.dayOfWeek != closestActiveDay)
    return newTriggerTime
}

fun List<DayOfWeek>.findClosestActiveDay(includeCurrent: Boolean): DayOfWeek? {
    if (isEmpty()) return null
    val currentDateTime = LocalDateTime.now()
    val currentDayOfWeek = currentDateTime.dayOfWeek
    if (includeCurrent) {
        if (contains(currentDayOfWeek)) return currentDayOfWeek
    }
    var changingDateTime = currentDateTime
    do {
        changingDateTime = changingDateTime.plusDays(1)
    } while (!contains(changingDateTime.dayOfWeek))
    return changingDateTime.dayOfWeek
}
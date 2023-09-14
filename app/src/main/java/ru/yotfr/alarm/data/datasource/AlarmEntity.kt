package ru.yotfr.alarm.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.yotfr.alarm.domain.model.Snooze
import ru.yotfr.alarm.domain.model.Sound
import java.time.DayOfWeek
import java.time.LocalDateTime

@Entity(tableName = "alarms")
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val triggerTime: LocalDateTime,
    val isActive: Boolean,
    val activeWeekDays: List<DayOfWeek>,
    val label: String,
    val snooze: Snooze,
    val sound: Sound,
    val vibrate: Boolean
)
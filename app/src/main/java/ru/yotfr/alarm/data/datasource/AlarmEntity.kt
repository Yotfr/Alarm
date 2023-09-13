package ru.yotfr.alarm.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalDateTime

@Entity(tableName = "alarms")
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val triggerTime: LocalDateTime,
    val isActive: Boolean,
    val activeWeekDays: List<DayOfWeek>,
    val label: String
)
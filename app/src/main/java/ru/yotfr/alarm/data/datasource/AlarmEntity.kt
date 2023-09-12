package ru.yotfr.alarm.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.yotfr.alarm.domain.model.WeekDays
import java.time.LocalDateTime

@Entity(tableName = "alarms")
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val triggerTime: LocalDateTime,
    val isActive: Boolean,
    val activeWeekDays: List<WeekDays>,
    val label: String
)
package ru.yotfr.alarm.data.datasource

import androidx.room.TypeConverter
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.ZoneOffset
class AlarmTypeConverter {

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): Long {
        return value.toEpochSecond(ZoneOffset.UTC)
    }

    @TypeConverter
    fun toLocalDateTime(value: Long): LocalDateTime {
        return LocalDateTime.ofEpochSecond(value, 0, ZoneOffset.UTC)
    }

    @TypeConverter
    fun toWeekDays(value: String): List<DayOfWeek> {
        if (value.isBlank()) return emptyList()
        return value.split(",").map { enumValueOf(it) }
    }

    @TypeConverter
    fun fromWeekDays(value: List<DayOfWeek>): String {
        if (value.isEmpty()) return ""
        return value.joinToString(separator = ",") { it.name }
    }

}


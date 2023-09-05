package ru.yotfr.alarm.data

import androidx.room.TypeConverter
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

}
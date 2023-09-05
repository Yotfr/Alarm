package ru.yotfr.alarm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        AlarmEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(AlarmTypeConverter::class)
abstract class AlarmDatabase: RoomDatabase() {
    abstract val alarmDao: AlarmDao
}
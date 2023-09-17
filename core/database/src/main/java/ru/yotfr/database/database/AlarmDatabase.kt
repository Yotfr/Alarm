package ru.yotfr.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.yotfr.database.dao.AlarmDao
import ru.yotfr.database.model.AlarmEntity
import ru.yotfr.database.util.AlarmTypeConverter

@Database(
    entities = [
        AlarmEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(AlarmTypeConverter::class)
abstract class AlarmDatabase: RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}
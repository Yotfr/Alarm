package ru.yotfr.alarm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarms")
    fun getAllAlarms(): Flow<List<AlarmEntity>>

    @Insert(entity = AlarmEntity::class)
    suspend fun insertAlarm(alarmEntity: AlarmEntity)
}
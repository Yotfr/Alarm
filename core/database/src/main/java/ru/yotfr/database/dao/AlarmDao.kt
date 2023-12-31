package ru.yotfr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.yotfr.database.model.AlarmEntity

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarms")
    fun getAllAlarms(): Flow<List<AlarmEntity>>

    @Insert(entity = AlarmEntity::class)
    suspend fun insertAlarm(alarmEntity: AlarmEntity) : Long

    @Update(entity = AlarmEntity::class)
    suspend fun updateAlarm(alarmEntity: AlarmEntity)

    @Delete(entity = AlarmEntity::class)
    suspend fun deleteAlarm(alarmEntity: AlarmEntity)

    @Query("SELECT * FROM alarms WHERE id = :id")
    suspend fun getById(id: Long): AlarmEntity
}
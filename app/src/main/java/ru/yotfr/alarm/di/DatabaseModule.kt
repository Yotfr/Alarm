package ru.yotfr.alarm.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.dao.AlarmDao
import ru.yotfr.database.database.AlarmDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAlarmDatabase(
        @ApplicationContext context: Context
    ): ru.yotfr.database.database.AlarmDatabase {
        return Room.databaseBuilder(
            context,
            ru.yotfr.database.database.AlarmDatabase::class.java,
            "alarms_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAlarmDao(
        alarmDatabase: ru.yotfr.database.database.AlarmDatabase
    ): ru.yotfr.database.dao.AlarmDao {
        return alarmDatabase.alarmDao
    }

}
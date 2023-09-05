package ru.yotfr.alarm.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.alarm.data.AlarmDao
import ru.yotfr.alarm.data.AlarmDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAlarmDatabase(
        @ApplicationContext context: Context
    ): AlarmDatabase {
        return Room.databaseBuilder(
            context,
            AlarmDatabase::class.java,
            "alarms_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAlarmDao(
        alarmDatabase: AlarmDatabase
    ): AlarmDao {
        return alarmDatabase.alarmDao
    }

}
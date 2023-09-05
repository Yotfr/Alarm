package ru.yotfr.alarm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import ru.yotfr.alarm.data.AlarmDao
import ru.yotfr.alarm.data.AlarmRepositoryImpl
import ru.yotfr.alarm.domain.AlarmRepository
import javax.inject.Singleton

@Module
@InstallIn()
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAlarmRepository(
        alarmDao: AlarmDao
    ): AlarmRepository {
        return AlarmRepositoryImpl(alarmDao)
    }

}
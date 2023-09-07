package ru.yotfr.alarm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.alarm.data.AlarmDao
import ru.yotfr.alarm.data.AlarmRepositoryImpl
import ru.yotfr.alarm.data.AlarmSchedulerImpl
import ru.yotfr.alarm.domain.AlarmRepository
import ru.yotfr.alarm.domain.AlarmScheduler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAlarmRepository(
        alarmDao: AlarmDao
    ): AlarmRepository {
        return AlarmRepositoryImpl(alarmDao)
    }

    @Provides
    @Singleton
    fun provideAlarmScheduler(
        @ApplicationContext context: Context
    ) : AlarmScheduler {
        return AlarmSchedulerImpl(context)
    }

}
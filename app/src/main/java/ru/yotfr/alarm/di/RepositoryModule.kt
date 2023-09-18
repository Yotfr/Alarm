package ru.yotfr.alarm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.dao.AlarmDao
import ru.yotfr.alarm.data.repository.AlarmSchedulerImpl
import ru.yotfr.alarm.domain.repository.AlarmScheduler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAlarmRepository(
        alarmDao: ru.yotfr.database.dao.AlarmDao
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
package ru.yotfr.alarmscheduler

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlarmSchedulerModule {

    @Provides
    fun provideAlarmSchedulerRepository(
        @ApplicationContext context: Context
    ): AlarmScheduler {
        return AlarmSchedulerImpl(context)
    }
}
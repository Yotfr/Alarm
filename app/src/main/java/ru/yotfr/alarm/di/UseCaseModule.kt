package ru.yotfr.alarm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.alarmscheduler.AlarmScheduler
import ru.yotfr.domain.ChangeAlarmTriggerTimeUseCase
import ru.yotfr.domain.CreateNewAlarmUseCase
import ru.yotfr.domain.DeleteAlarmUseCase
import ru.yotfr.domain.DismissAlarmUseCase
import ru.yotfr.domain.GetAlarmByIdUseCase
import ru.yotfr.domain.GetAllAlarmsUseCase
import ru.yotfr.domain.ToggleAlarmStatusUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllAlarmsUseCase(alarmRepository: AlarmRepository): ru.yotfr.domain.GetAllAlarmsUseCase {
        return ru.yotfr.domain.GetAllAlarmsUseCase(alarmRepository)
    }

    @Provides
    fun provideCreateNewAlarmUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): ru.yotfr.domain.CreateNewAlarmUseCase {
        return ru.yotfr.domain.CreateNewAlarmUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideToggleAlarmStatusUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): ru.yotfr.domain.ToggleAlarmStatusUseCase {
        return ru.yotfr.domain.ToggleAlarmStatusUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideDeleteAlarmUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): ru.yotfr.domain.DeleteAlarmUseCase {
        return ru.yotfr.domain.DeleteAlarmUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideAlarmTriggerTimeUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): ru.yotfr.domain.ChangeAlarmTriggerTimeUseCase {
        return ru.yotfr.domain.ChangeAlarmTriggerTimeUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideDismissAlarmUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): ru.yotfr.domain.DismissAlarmUseCase {
        return ru.yotfr.domain.DismissAlarmUseCase(
            alarmScheduler,
            alarmRepository
        )
    }

    @Provides
    fun provideGetAlarmByIdUseCase(alarmRepository: AlarmRepository): ru.yotfr.domain.GetAlarmByIdUseCase {
        return ru.yotfr.domain.GetAlarmByIdUseCase(alarmRepository)
    }
}
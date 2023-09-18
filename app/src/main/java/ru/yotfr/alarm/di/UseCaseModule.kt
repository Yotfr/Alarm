package ru.yotfr.alarm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.alarmscheduler.AlarmScheduler
import ru.yotfr.alarm.domain.usecase.ChangeAlarmTriggerTimeUseCase
import ru.yotfr.alarm.domain.usecase.CreateNewAlarmUseCase
import ru.yotfr.alarm.domain.usecase.DeleteAlarmUseCase
import ru.yotfr.alarm.domain.usecase.DismissAlarmUseCase
import ru.yotfr.alarm.domain.usecase.GetAlarmByIdUseCase
import ru.yotfr.alarm.domain.usecase.GetAllAlarmsUseCase
import ru.yotfr.alarm.domain.usecase.ToggleAlarmStatusUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllAlarmsUseCase(alarmRepository: AlarmRepository): GetAllAlarmsUseCase {
        return GetAllAlarmsUseCase(alarmRepository)
    }

    @Provides
    fun provideCreateNewAlarmUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): CreateNewAlarmUseCase {
        return CreateNewAlarmUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideToggleAlarmStatusUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): ToggleAlarmStatusUseCase {
        return ToggleAlarmStatusUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideDeleteAlarmUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): DeleteAlarmUseCase {
        return DeleteAlarmUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideAlarmTriggerTimeUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): ChangeAlarmTriggerTimeUseCase {
        return ChangeAlarmTriggerTimeUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideDismissAlarmUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: ru.yotfr.alarmscheduler.AlarmScheduler
    ): DismissAlarmUseCase {
        return DismissAlarmUseCase(
            alarmScheduler,
            alarmRepository
        )
    }

    @Provides
    fun provideGetAlarmByIdUseCase(alarmRepository: AlarmRepository): GetAlarmByIdUseCase {
        return GetAlarmByIdUseCase(alarmRepository)
    }
}
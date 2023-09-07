package ru.yotfr.alarm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.yotfr.alarm.domain.AlarmRepository
import ru.yotfr.alarm.domain.AlarmScheduler
import ru.yotfr.alarm.domain.CreateNewAlarmUseCase
import ru.yotfr.alarm.domain.DeleteAlarmUseCase
import ru.yotfr.alarm.domain.GetAllAlarmsUseCase
import ru.yotfr.alarm.domain.ToggleAlarmStatusUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllAlarmsUseCase(alarmRepository: AlarmRepository): GetAllAlarmsUseCase {
        return GetAllAlarmsUseCase(alarmRepository)
    }

    @Provides
    fun provideCreateNewAlarmUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: AlarmScheduler
    ): CreateNewAlarmUseCase {
        return CreateNewAlarmUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideToggleAlarmStatusUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: AlarmScheduler
    ): ToggleAlarmStatusUseCase {
        return ToggleAlarmStatusUseCase(
            alarmRepository,
            alarmScheduler
        )
    }

    @Provides
    fun provideDeleteAlarmStatusUseCase(
        alarmRepository: AlarmRepository,
        alarmScheduler: AlarmScheduler
    ): DeleteAlarmUseCase {
        return DeleteAlarmUseCase(
            alarmRepository,
            alarmScheduler
        )
    }
}
package ru.yotfr.alarm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.yotfr.alarm.domain.AlarmRepository
import ru.yotfr.alarm.domain.CreateNewAlarmUseCase
import ru.yotfr.alarm.domain.GetAllAlarmsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllAlarmsUseCase(alarmRepository: AlarmRepository): GetAllAlarmsUseCase {
        return GetAllAlarmsUseCase(alarmRepository)
    }

    @Provides
    fun provideCreateNewAlarmUseCase(alarmRepository: AlarmRepository): CreateNewAlarmUseCase {
        return CreateNewAlarmUseCase(alarmRepository)
    }
}
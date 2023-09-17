package ru.yotfr.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.dao.AlarmDao
import ru.yotfr.database.database.AlarmDatabase

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun provideAlarmDao(
        database: AlarmDatabase
    ): AlarmDao = database.alarmDao()
}
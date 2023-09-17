package ru.yotfr.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.database.AlarmDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAlarmDatabase(
        @ApplicationContext context: Context
    ): AlarmDatabase = Room.databaseBuilder(
        context,
        AlarmDatabase::class.java,
        "alarm_db"
    ).build()
}
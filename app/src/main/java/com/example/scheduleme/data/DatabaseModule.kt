package com.example.scheduleme.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{
    @Provides // описание, как создать объект
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "schedule_me.db"
        ).build()
    }

    @Provides
    fun provideShiftTemplateDao(
        database: AppDatabase
    ): ShiftTemplateDao{
        return database.shiftTemplateDao()
    }

    @Provides
    @Singleton
    fun provideShiftTemplateRepository(
        dao: ShiftTemplateDao
    ): ShiftTemplateRepository{
        return ShiftTemplateRepository(dao)
    }

    @Provides
    fun provideShiftAssignmentDao(
        database: AppDatabase
    ): ShiftAssignmentDao{
        return database.shiftAssignmentDao()
    }

    @Provides
    @Singleton
    fun provideShiftAssignmentRepository(
        dao: ShiftAssignmentDao
    ): ShiftAssignmentRepository{
        return ShiftAssignmentRepository(dao)
    }

}
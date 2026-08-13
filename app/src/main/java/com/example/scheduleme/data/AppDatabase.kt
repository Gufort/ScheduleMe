package com.example.scheduleme.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.scheduleme.model.ShiftAssignment
import com.example.scheduleme.model.ShiftTemplateEntity

@Database(
    entities = [
        ShiftTemplateEntity::class,
        ShiftAssignment::class
    ],
    version = 2
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shiftTemplateDao(): ShiftTemplateDao
    abstract fun shiftAssignmentDao(): ShiftAssignmentDao
}
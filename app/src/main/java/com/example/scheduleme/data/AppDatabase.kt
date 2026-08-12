package com.example.scheduleme.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.scheduleme.model.ShiftTemplateEntity

@Database(
    entities = [
        ShiftTemplateEntity::class
    ],
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shiftTemplateDao(): ShiftTemplateDao
}
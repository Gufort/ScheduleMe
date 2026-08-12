package com.example.scheduleme.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalTime

class RoomConverters {
    @TypeConverters
    fun fromLocalTime(value: LocalTime): String{
        return value.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalTime(value: String): LocalTime {
        return LocalTime.parse(value)
    }
}
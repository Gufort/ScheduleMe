package com.example.scheduleme.ui.calendar

import java.time.LocalTime

data class ShiftTemplate(
    val id: Long,
    val name: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val description: String
)
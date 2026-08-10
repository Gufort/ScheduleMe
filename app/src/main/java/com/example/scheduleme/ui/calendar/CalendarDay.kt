package com.example.scheduleme.ui.calendar

import java.time.LocalDate

data class CalendarDay(
    val date: LocalDate,
    val isWeekEnd: Boolean,
    val isCurrentMonth: Boolean
)

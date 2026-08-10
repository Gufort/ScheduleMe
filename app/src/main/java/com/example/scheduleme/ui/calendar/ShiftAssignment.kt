package com.example.scheduleme.ui.calendar

import java.time.LocalDate

data class ShiftAssignment(
    val date: LocalDate,
    val shiftTemplateId: Long
)
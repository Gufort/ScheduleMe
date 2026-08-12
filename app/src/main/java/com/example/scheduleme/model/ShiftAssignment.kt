package com.example.scheduleme.model

import java.time.LocalDate

data class ShiftAssignment(
    val date: LocalDate,
    val shiftTemplateId: Long
)
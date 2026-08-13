package com.example.scheduleme.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "shift_assignments")
data class ShiftAssignment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: LocalDate,
    val template: ShiftTemplateEntity
)
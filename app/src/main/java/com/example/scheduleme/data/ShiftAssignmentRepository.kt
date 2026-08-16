package com.example.scheduleme.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.scheduleme.model.ShiftAssignment
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ShiftAssignmentRepository(
    private val dao : ShiftAssignmentDao
) {
    suspend fun createShift(
        shift : ShiftAssignment
    ) : Long{
        return dao.insert(shift)
    }

    suspend fun updateShift(
        shift: ShiftAssignment
    ){
        dao.update(shift)
    }

    suspend fun deleteShift(
        shift : ShiftAssignment
    ){
        dao.delete(shift)
    }

    suspend fun getAllShifts() : List<ShiftAssignment>{
        return dao.getAll()
    }

    suspend fun getShiftById(
        id : Long
    ) : ShiftAssignment?{
        return dao.getById(id)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun duplicateSector(
        startDate: LocalDate,
        endDate: LocalDate,
        repeatCount: Int
    ){
        val shifts = dao.getBetweenDates(
            startDate,
            endDate
        )

        val sectorLength =
            ChronoUnit.DAYS.between(startDate, endDate) + 1

        for(i in 1..repeatCount){
            val offset = sectorLength * i
            shifts.forEach { shift ->
                val newShift = ShiftAssignment(
                    date = shift.date.plusDays(offset),
                    templateId = shift.templateId
                )

                dao.insert(newShift)
            }
        }
    }
}
package com.example.scheduleme.data

import com.example.scheduleme.model.ShiftAssignment

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
}
package com.example.scheduleme.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.scheduleme.model.ShiftAssignment

@Dao
interface ShiftAssignmentDao {
    @Insert
    suspend fun insert(
        shift: ShiftAssignment
    ) : Long

    @Update
    suspend fun update(
        shift: ShiftAssignment
    )

    @Delete
    suspend fun delete(
        shift : ShiftAssignment
    )

    @Query("SELECT * FROM shift_assignments")
    suspend fun getAll(): List<ShiftAssignment>

    @Query("SELECT * FROM shift_assignments WHERE id = :id")
    suspend fun getById(id: Long) : ShiftAssignment?
}
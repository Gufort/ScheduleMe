package com.example.scheduleme.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.scheduleme.model.ShiftTemplateEntity

@Dao
interface ShiftTemplateDao {
    @Insert
    suspend fun insert(
        template: ShiftTemplateEntity
    ) : Long

    @Update
    suspend fun update(
        template: ShiftTemplateEntity
    )

    @Delete
    suspend fun delete(
        template: ShiftTemplateEntity
    )

    @Query("SELECT * FROM shift_templates")
    suspend fun getAll(): List<ShiftTemplateEntity>

    @Query("SELECT * FROM shift_templates WHERE id = :id")
    suspend fun getById(id: Long) : ShiftTemplateEntity?
}
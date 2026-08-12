package com.example.scheduleme.data

import com.example.scheduleme.model.ShiftTemplateEntity

class ShiftTemplateRepository (
    private val dao : ShiftTemplateDao
){
    suspend fun createTemplate(
        template: ShiftTemplateEntity
    ): Long{
        return dao.insert(template)
    }

    suspend fun updateTemplate(
        template: ShiftTemplateEntity
    ) {
        dao.update(template)
    }

    suspend fun deleteTemplate(
        template: ShiftTemplateEntity
    ) {
        dao.delete(template)
    }

    suspend fun getAllTemplates(): List<ShiftTemplateEntity> {
        return dao.getAll()
    }

    suspend fun getTemplateById(
        id: Long
    ): ShiftTemplateEntity? {
        return dao.getById(id)
    }
}
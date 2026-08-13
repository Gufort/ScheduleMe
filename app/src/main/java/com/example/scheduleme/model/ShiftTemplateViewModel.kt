package com.example.scheduleme.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleme.data.ShiftTemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ShiftTemplateViewModel @Inject constructor(
    private val repository: ShiftTemplateRepository
) : ViewModel() {
    fun createTemplate(
        name: String,
        startTime: LocalTime,
        endTime: LocalTime,
        description: String
    ){
        viewModelScope.launch {
            val template = ShiftTemplateEntity(
                name = name,
                startTime = startTime,
                endTime = endTime,
                description = description
            )

            repository.createTemplate(template)
        }
    }
}
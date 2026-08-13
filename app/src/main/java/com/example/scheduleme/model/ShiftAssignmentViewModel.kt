package com.example.scheduleme.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleme.data.ShiftAssignmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ShiftAssignmentViewModel @Inject constructor(
    private val repository: ShiftAssignmentRepository
): ViewModel() {
    var assignments by mutableStateOf<List<ShiftAssignment>>(emptyList())
        private set
    fun createShiftDay(
        date: LocalDate,
        template : ShiftTemplateEntity
    ){
        viewModelScope.launch {
            repository.createShift(ShiftAssignment(date = date, templateId = template.id))
        }
        loadAssignments()
    }

    fun loadAssignments(){
        viewModelScope.launch {
            assignments = repository.getAllShifts()
        }
    }
}
package com.example.scheduleme.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scheduleme.data.ShiftTemplateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShiftTemplateViewModel @Inject constructor(
    private val repository: ShiftTemplateRepository
) : ViewModel() {
    var templates by mutableStateOf<List<ShiftTemplateEntity>>(emptyList())
       private set
    fun createTemplate(
        template: ShiftTemplateEntity,
        onCreated: (ShiftTemplateEntity) -> Unit
    ) {
        viewModelScope.launch {
            val id = repository.createTemplate(template)
            onCreated(template.copy(id = id))
        }
    }

    fun loadTemplates(){
        viewModelScope.launch {
            templates = repository.getAllTemplates()
        }
    }

    fun deleteAllTemplates(){
        viewModelScope.launch {
            for(t in templates){
                repository.deleteTemplate(t)
            }
            templates = emptyList()
        }
    }
}
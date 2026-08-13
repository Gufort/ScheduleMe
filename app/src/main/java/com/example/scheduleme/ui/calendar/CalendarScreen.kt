package com.example.scheduleme.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scheduleme.model.ShiftAssignmentViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(openSettings: () -> Unit){
    var currentDate by remember{
        mutableStateOf(YearMonth.now())
    }
    var showShiftActionDialog by remember {
        mutableStateOf(false)
    }

    var showCreateTemplateDialog by remember {
        mutableStateOf(false)
    }

    var showChoiceTemplateDialog by remember {
        mutableStateOf(false)
    }
    val viewModel : ShiftAssignmentViewModel = hiltViewModel()
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton( onClick = openSettings ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Настройки"
                    )
                }
            }

            CalendarHeader(
                date = currentDate,
                onPreviousClick = {
                    currentDate = currentDate.minusMonths(1)
                },
                onNextClick = {
                    currentDate = currentDate.plusMonths(1)
                }
            )
            WeekHeader()
            CalendarGrid(currentDate, onDayClick = { date ->
                showShiftActionDialog = true
                selectedDate = date
            }, selectedDate)

            if (showShiftActionDialog) {
                ShiftActionDialog(
                    onCreateTemplate = {
                        showShiftActionDialog = false
                        showCreateTemplateDialog = true
                    },
                    onChooseTemplate = {
                        showShiftActionDialog = false
                        showChoiceTemplateDialog = true
                    },
                    onDismiss = {
                        showShiftActionDialog = false
                        selectedDate = null
                    }
                )
            }

            if (showCreateTemplateDialog) {
                selectedDate?.let { date ->
                    ShiftDialog(
                        date = date,
                        onDismiss = {
                            showCreateTemplateDialog = false
                            selectedDate = null
                        },
                        onTemplateCreated = { name ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    "Шаблон смены $name успешно создан!"
                                )
                            }
                        }
                    )
                }
            }

            if (showChoiceTemplateDialog) {
                selectedDate?.let { date ->
                    ChoiceShiftTemplateDialog(
                        onDismiss = {
                            showChoiceTemplateDialog = false
                            selectedDate = null
                        },
                        onTemplateSelected = { template ->
                            viewModel.createShiftDay(date, template)
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    "Рабочая смена ${template.name} на дату $date добавлена!"
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}
package com.example.scheduleme.ui.calendar

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scheduleme.model.ShiftTemplateViewModel
import java.time.LocalDate
import java.time.LocalTime

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShiftDialog(
    date: LocalDate,
    viewModel: ShiftTemplateViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onTemplateCreated: (String) -> Unit
){
    var name by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf(LocalTime.of(8, 0)) }
    var endTime by remember { mutableStateOf(LocalTime.of(21, 0)) }
    var showEndTimePicker by remember { mutableStateOf(false) }
    var showStartTimePicker by remember { mutableStateOf(false) }
    var description by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            tonalElevation = 6.dp
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ){
                Text(
                    text = "Создание темы",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Дата: ${date.dayOfMonth}.${date.month.value}.${date.year}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Название") },
                    placeholder = { Text("Введите текст...") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { showStartTimePicker = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Начало")
                            Text(
                                text = String.format(
                                    "%02d:%02d",
                                    startTime.hour,
                                    startTime.second
                                )
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = {
                            showEndTimePicker = true
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Конец")
                            Text(
                                text = String.format(
                                    "%02d:%02d",
                                    endTime.hour,
                                    endTime.minute
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Описание") },
                    placeholder = { Text(text = "Введите текст...") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ){
                    TextButton(
                        onClick = {
                            viewModel.createTemplate(
                                name,
                                startTime,
                                endTime,
                                description
                            )
                            onTemplateCreated(name)
                            onDismiss()
                        }
                    ) {
                        Text(text = "Создать")
                    }
                    TextButton(onClick = onDismiss) {
                        Text(text = "Отмена")
                    }
                }

                if (showStartTimePicker) {
                    TimePickerDialog(
                        initialTime = startTime,
                        onTimeSelected = { time ->
                            startTime = time
                            showStartTimePicker = false
                        },
                        onDismiss = {
                            showStartTimePicker = false
                        }
                    )
                }

                if (showEndTimePicker) {
                    TimePickerDialog(
                        initialTime = endTime,
                        onTimeSelected = { time ->
                            endTime = time
                            showEndTimePicker = false
                        },
                        onDismiss = {
                            showEndTimePicker = false
                        }
                    )
                }
            }
        }
    }
}
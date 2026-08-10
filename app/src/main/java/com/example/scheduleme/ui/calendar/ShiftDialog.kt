package com.example.scheduleme.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShiftDialog(
    date: LocalDate,
    onDismiss: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Создание смены") },
        text = { Text(text = "Дата: ${date.dayOfMonth}.${date.monthValue}.${date.year}")},
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Создать")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Отмена")
            }
        }
    )
}
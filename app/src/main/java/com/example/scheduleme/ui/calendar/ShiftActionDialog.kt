package com.example.scheduleme.ui.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ShiftActionDialog(
    onCreateTemplate: () -> Unit,
    onChooseTemplate: () -> Unit,
    onDismiss: () -> Unit
){
    Dialog(
        onDismissRequest = onDismiss
    ){
        Surface(
            shape = RoundedCornerShape(20.dp),
            tonalElevation = 6.dp
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Выберите действие",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onCreateTemplate
                ){
                    Text(text = "Создать шаблон смены")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onChooseTemplate
                ) {
                    Text(text = "Выбрать существующий шаблон")
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(
                    onClick = onDismiss
                ) {
                    Text("Отмена")
                }
            }
        }
    }
}
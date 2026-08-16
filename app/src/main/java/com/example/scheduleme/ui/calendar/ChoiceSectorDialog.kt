package com.example.scheduleme.ui.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun ChoiceSectorDialog(
    onDismiss: () -> Unit,
    onRepeat: (Int) -> Unit
){
    var countOfRepetitions by remember {
        mutableStateOf("")
    }
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
                    .padding(24.dp)
            ){
                Text(
                    text = "Количество повторений сектора",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = countOfRepetitions,
                    onValueChange = { countOfRepetitions = it },
                    label = { Text(text = "Количество повторов") },
                    placeholder = { Text(text = "Введите текст...") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    TextButton(
                        onClick = {
                            val count = countOfRepetitions.toIntOrNull()

                            if (count != null && count > 0) {
                                onRepeat(count)
                            }

                            onDismiss()
                        }
                    ) {
                        Text("Готово")
                    }

                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text("Отмена")
                    }
                }
            }
        }
    }
}
package com.example.scheduleme.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.scheduleme.model.ShiftAssignment
import com.example.scheduleme.model.ShiftTemplateEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DescriptionDialog(
    shiftDate: ShiftAssignment,
    shiftTemplate: ShiftTemplateEntity,
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
                    text = "${shiftDate.date.dayOfMonth} " +
                            "${shiftDate.date.month} " +
                            "${shiftDate.date.year}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Название: ${shiftTemplate.name}",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Время",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "${shiftTemplate.startTime} — ${shiftTemplate.endTime}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if(shiftTemplate.description.isNotBlank()){
                    Text(
                        text = "Описание",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = shiftTemplate.description,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Закрыть")
                }
            }
        }
    }
}
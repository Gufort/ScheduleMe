package com.example.scheduleme.ui.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("RememberReturnType")
@Composable
fun SettingsScreen(
    onThemeChange: (Boolean) -> Unit,
    onBack: () -> Unit,
    darkTheme: Boolean
){
    var expanded by remember{
        mutableStateOf(false)
    }
    val selectedTheme = if (darkTheme) "Темная" else "Светлая"
    Column(
        modifier = Modifier.fillMaxSize().padding(start = 15.dp, top = 50.dp, end = 15.dp, bottom = 15.dp)
    ){
        Text(text = "Настройки")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Тема: ")
            Box{
                OutlinedButton(
                    onClick = { expanded = true }
                ) {
                    Text(selectedTheme)

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Светлая") },
                        onClick = {
                            onThemeChange(false)
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Темная") },
                        onClick = {
                            onThemeChange(true)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
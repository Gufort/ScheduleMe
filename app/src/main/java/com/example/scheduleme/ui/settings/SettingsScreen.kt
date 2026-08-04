package com.example.scheduleme.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(){
    Column(
        modifier = Modifier.fillMaxSize().padding(start = 15.dp, top = 50.dp, end = 15.dp, bottom = 15.dp)
    ){
        Text(text = "Настройки")
    }
}
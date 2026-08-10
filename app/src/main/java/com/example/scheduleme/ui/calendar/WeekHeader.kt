package com.example.scheduleme.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeekHeader(){
    val weekDays = listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.height(50.dp).background(MaterialTheme.colorScheme.primary)
    ) {
        items(weekDays.size) { index ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = weekDays[index], color = if(index == 5 || index == 6) Color.Red else Color.Black)
            }
        }
    }
}
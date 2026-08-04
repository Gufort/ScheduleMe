package com.example.scheduleme.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarGrid(date: YearMonth){
    val listOfDays = buildCalendarDays(date.year, date.monthValue)
    var cellCount = if(listOfDays[35] == null) 35 else 42
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(cellCount){ index ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${listOfDays.get(index)}")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun buildCalendarDays(year: Int, month: Int) : List<Int?>{
    val yearMonth = YearMonth.of(year, month)
    var index = yearMonth.atDay(1).dayOfWeek.value
    val result = mutableListOf<Int?>()

    for (i in 1 until index) {
        result.add(null)
    }
    for(i in 1..yearMonth.lengthOfMonth()){
        result.add(i)
    }
    while (result.size < 42) {
        result.add(null)
    }

    return result
}
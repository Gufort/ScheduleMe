package com.example.scheduleme.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.scheduleme.model.CalendarDay
import com.example.scheduleme.model.ShiftAssignment
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarGrid(date: YearMonth, onDayClick: (LocalDate) -> Unit, selectedDate: LocalDate?, assignments: List<ShiftAssignment>){
    val listOfDays = buildCalendarDays(date.year, date.monthValue)
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(listOfDays.size){ index ->
            val day = listOfDays[index]
            val hasShift = assignments.any{
                it.date == day.date
            }
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(1.dp, Color.Gray)
                    .background(if (selectedDate == day.date) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)
                    .clickable {
                        onDayClick(day.date) },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${day.date.dayOfMonth}",
                    color = if(day.isWeekEnd) Color.Red else Color.Black,
                    modifier = Modifier.align(Alignment.Center))

                if(hasShift){
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Смена назначена",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                            .size(18.dp)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun buildCalendarDays(year: Int, month: Int) : List<CalendarDay>{
    val currentMonth = YearMonth.of(year, month)
    val firstDay = currentMonth.atDay(1)
    val startOffset = firstDay.dayOfWeek.value - 1
    val startDate = firstDay.minusDays(startOffset.toLong())

    val result =  List(42){ index ->
        val date = startDate.plusDays(index.toLong())
        CalendarDay(
            date = date,
            isWeekEnd = date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY,
            isCurrentMonth = YearMonth.from(date) == currentMonth
        )
    }

    if(result[35].isCurrentMonth) return result
    else return result.take(35)
}
package com.example.scheduleme.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen(){
    var currentDate by remember{
        mutableStateOf(YearMonth.now())
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(start = 15.dp, top = 50.dp, end = 15.dp, bottom = 15.dp)
    ) {
        CalendarHeader(
            date = currentDate,
            onPreviousClick = {
                currentDate = currentDate.minusMonths(1)
            },
            onNextClick = {
                currentDate = currentDate.plusMonths(1)
            }
        )
        WeekHeader()
        CalendarGrid(currentDate)
    }
}
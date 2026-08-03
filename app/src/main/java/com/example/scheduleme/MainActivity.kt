package com.example.scheduleme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scheduleme.ui.theme.ScheduleMeTheme
import com.airbnb.lottie.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScheduleMeTheme {
                var showAnimation by remember { // сохраняет значение между запусками composable ф-й
                    mutableStateOf(true)
                }
                if(showAnimation){
                    StartAnimation {
                        showAnimation = false
                    }
                }
                else{
                    CalendarScreen()
                }
            }
        }
    }
}

@Composable
fun StartAnimation(onFinished: () -> Unit){
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.start_animation)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )
    LaunchedEffect(progress) {
        if(progress == 1f)
            onFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(250.dp)
        )
    }
}


@Composable
fun CalendarScreen(){
    Column(
        modifier = Modifier.fillMaxSize().padding(start = 15.dp, top = 50.dp, end = 15.dp, bottom = 15.dp)
    ) {
        CalendarHeader("Май 2026")
        WeekHeader()
        CalendarGrid()
    }
}

@Composable
fun CalendarHeader(date: String){
    Box(
        modifier = Modifier.fillMaxWidth().height(50.dp).border(1.dp, Color.Gray),
        contentAlignment = Alignment.Center
    ){
        Text(text = date)
    }
}

@Composable
fun WeekHeader(){
    val weekDays = listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.height(50.dp)
    ) {
        items(weekDays.size) { index ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = weekDays[index])
            }
        }
    }
}

@Composable
fun CalendarGrid(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(42){ index ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(1.dp, Color.Gray),
                contentAlignment = Alignment.Center
            ) {
              Text(text = "${index + 1}")
            }
        }
    }
}
package com.example.scheduleme

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.scheduleme.ui.animations.StartAnimation
import com.example.scheduleme.ui.calendar.CalendarScreen
import com.example.scheduleme.ui.theme.ScheduleMeTheme
import androidx.navigation.compose.rememberNavController
import com.example.scheduleme.navigation.NavGraph

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
                    val navController = rememberNavController()

                    NavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}
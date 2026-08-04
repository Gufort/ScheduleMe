package com.example.scheduleme.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.scheduleme.ui.calendar.CalendarScreen
import com.example.scheduleme.ui.settings.SettingsScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController, onThemeChange: (Boolean) -> Unit, darkTheme: Boolean){
    NavHost(
        navController = navController,
        startDestination = Screen.Calendar.route
    ){
        composable(Screen.Calendar.route){
            CalendarScreen(
                openSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.Settings.route){
            SettingsScreen(
                onThemeChange = onThemeChange,
                onBack = {
                    navController.popBackStack()
                },
                darkTheme = darkTheme
            )
        }
    }
}
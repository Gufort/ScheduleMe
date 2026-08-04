package com.example.scheduleme.navigation

sealed class Screen(val route: String){
    data object Calendar: Screen("calendar")
    data object Settings: Screen("settings")
}
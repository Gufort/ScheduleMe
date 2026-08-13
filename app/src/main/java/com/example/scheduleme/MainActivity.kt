package com.example.scheduleme

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.scheduleme.navigation.NavGraph
import com.example.scheduleme.ui.animations.StartAnimation
import com.example.scheduleme.model.SettingsViewModel
import com.example.scheduleme.ui.theme.ScheduleMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: SettingsViewModel = hiltViewModel()
            val darkTheme by viewModel.darkTheme.collectAsStateWithLifecycle()
            ScheduleMeTheme (darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
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
}
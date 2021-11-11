package com.io.coder.presentation.util

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.io.coder.presentation.error_screen.ErrorScreen
import com.io.coder.presentation.main_screen.MainScreen
import com.io.coder.presentation.main_screen.MainViewModel

@Composable
fun Navigation(activity: ComponentActivity){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ErrorScreen.route){

        composable(Screen.MainScreen.route){
            MainScreen(
                navController = navController,
                viewModel = ViewModelProvider(activity)[MainViewModel::class.java]
            )
        }

        composable(Screen.CoderScreen.route){

        }

        composable(Screen.ErrorScreen.route){
            ErrorScreen(
                navController = navController
            )
        }
    }
}
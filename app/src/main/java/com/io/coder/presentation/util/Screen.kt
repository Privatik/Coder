package com.io.coder.presentation.util

sealed class Screen(val route: String){
    object MainScreen: Screen("main_screen")
    object CoderScreen: Screen("coder_screen")
}

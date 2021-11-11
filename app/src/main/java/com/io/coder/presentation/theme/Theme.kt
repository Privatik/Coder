package com.io.coder.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val colorPalette = darkColors(
    primary = GrayDark,
    primaryVariant = Gray,
    secondary = GrayMoreLight,
    secondaryVariant = GrayLight,
    background = Color.White,
    onPrimary = Color.Black,
    onSecondary = Purple,
    onBackground = Color.Black
)

@Composable
fun CoderTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Transparent)

    MaterialTheme(
        colors = colorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
package com.io.coder.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val colorPalette = darkColors(
    primary = Color.White,
    primaryVariant = Gray,
    secondary = GrayLight,
    background = Color.White,
    onPrimary = Color.Black,
    onSecondary = Purple,
    onBackground = Color.Black
)

@Composable
fun CoderTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = colorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
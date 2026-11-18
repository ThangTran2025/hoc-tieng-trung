package com.chineselearn.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Red = Color(0xFFC62828)
val Cream = Color(0xFFFFF8E1)
val GoldAccent = Color(0xFFFFB300)
val ImperialRedLight = Color(0xFFE53935)
val JadeGreen = Color(0xFF00897B)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = lightColorScheme(primary = Red, background = Cream, surface = Cream), content = content)
}

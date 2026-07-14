package com.smartstart.synergy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val LightColors = lightColorScheme(
    primary = Purple,
    onPrimary = Color.White,
    primaryContainer = SunnyYellow,
    onPrimaryContainer = InkDark,
    secondary = Teal,
    onSecondary = Color.White,
    tertiary = Coral,
    onTertiary = Color.White,
    background = OffWhite,
    onBackground = InkDark,
    surface = Color.White,
    onSurface = InkDark,
)

private val DarkColors = darkColorScheme(
    primary = SunnyYellow,
    onPrimary = InkDark,
    secondary = Teal,
    tertiary = Coral,
    background = Color(0xFF120A22),
    onBackground = OffWhite,
    surface = Color(0xFF1D1233),
    onSurface = OffWhite,
)

private val AppTypography = Typography(
    headlineLarge = Typography().headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
    headlineMedium = Typography().headlineMedium.copy(fontWeight = FontWeight.Bold),
    titleLarge = Typography().titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
    bodyLarge = Typography().bodyLarge.copy(fontSize = 18.sp),
)

@Composable
fun SmartStartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content,
    )
}

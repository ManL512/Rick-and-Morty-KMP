package com.losmoviles.shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light
private val LightColors = lightColorScheme(
    primary   = RickCyan,     // antes: 0xFF00B5CC
    secondary = PortalGreen,  // antes: 0xFF97CE4C
    tertiary  = MortyYellow,  // antes: 0xFFF9D648

    background = Color(0xFFF7F7F7),
    surface    = Color(0xFFFFFFFF),

    onPrimary   = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF000000),
    onTertiary  = Color(0xFF000000),
    onBackground= Color(0xFF000000),
    onSurface   = Color(0xFF000000),
)

// Dark
private val DarkColors = darkColorScheme(
    // Puedes reutilizar los mismos o crear variantes “lighten” si quieres
    primary   = RickCyan,
    secondary = PortalGreen,
    tertiary  = MortyYellow,

    background = Color(0xFF101010),
    surface    = Color(0xFF1C1C1C),

    // Ojo con el contraste: onPrimary negro si tu primary queda muy claro en dark
    onPrimary   = Color(0xFF000000),
    onSecondary = Color(0xFF000000),
    onTertiary  = Color(0xFF000000),
    onBackground= Color(0xFFFFFFFF),
    onSurface   = Color(0xFFFFFFFF),
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}
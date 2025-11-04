package com.losmoviles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.losmoviles.shared.ui.MainScaffold
import com.losmoviles.shared.ui.theme.AppTheme

@Composable
fun App() {
    val navController = rememberNavController()

    // Estado global del tema
    var isDarkTheme by rememberSaveable { mutableStateOf(false) }

    AppTheme(darkTheme = isDarkTheme) {
        MainScaffold(
            navController = navController,
            isDarkTheme = isDarkTheme,
            onToggleTheme = { isDarkTheme = !isDarkTheme }
        )
    }
}
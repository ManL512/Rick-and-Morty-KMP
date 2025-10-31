package com.losmoviles

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.losmoviles.shared.ui.MainScaffold
import com.losmoviles.shared.ui.theme.AppTheme

@Composable
fun App() {
    val navController = rememberNavController()

    AppTheme {
        MainScaffold(navController = navController)
    }
}

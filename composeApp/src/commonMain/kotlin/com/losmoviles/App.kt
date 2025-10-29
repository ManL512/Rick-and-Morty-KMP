package com.losmoviles

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.losmoviles.shared.navigation.AppNavGraph

@Composable
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        AppNavGraph(navController = navController)
    }
}

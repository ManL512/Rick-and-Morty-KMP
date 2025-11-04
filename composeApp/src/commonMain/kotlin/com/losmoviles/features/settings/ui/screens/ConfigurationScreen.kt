package com.losmoviles.features.settings.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.losmoviles.shared.ui.screens.CustomScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen(
    onBack: (() -> Unit)? = null,
    onToggleTheme: () -> Unit,
    isDarkTheme: Boolean
) {
    CustomScreen(
        title = "Settings",
        onBack = onBack,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("App Appearance", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(24.dp))
            val buttonLabel = if (isDarkTheme) {
                "Activar modo claro"
            } else "Activar modo oscuro"
            Button(onClick = onToggleTheme) {
                Text(buttonLabel)
            }

        }
    }
}

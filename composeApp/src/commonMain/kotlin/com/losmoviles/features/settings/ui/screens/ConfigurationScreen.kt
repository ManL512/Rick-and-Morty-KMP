package com.losmoviles.features.settings.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.losmoviles.shared.ui.screens.CustomScreen
import com.losmoviles.shared.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigurationScreen() {
    // 🔘 Estado para controlar modo claro/oscuro
    var isDarkTheme by remember { mutableStateOf(false) }

    AppTheme(darkTheme = isDarkTheme) {
        CustomScreen(
            title = "Configuration",
            onBack = null,
            actions = {
                IconButton(onClick = { /* Podrías añadir más acciones aquí */ }) {
                    Icon(Icons.Filled.Settings, contentDescription = "Settings")
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isDarkTheme) "Dark Mode Enabled" else "Light Mode Enabled",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = { isDarkTheme = !isDarkTheme },
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text(
                        text = if (isDarkTheme) "Switch to Light Mode" else "Switch to Dark Mode"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfigurationScreenPreview() {
    ConfigurationScreen()
}

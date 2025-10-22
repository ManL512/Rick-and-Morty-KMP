package com.losmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.losmoviles.shared.ui.screens.CustomScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CustomScreenPreviewContent() // 👈 prueba directa del CustomScreen
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScreenPreviewContent() {
    CustomScreen(
        title = "Pantalla de prueba",
        onBack = { /* No hace nada aquí */ },
        actions = {
            IconButton(onClick = { /* Acción futura */ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorito"
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Acción FAB */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Hola desde CustomScreen", style = MaterialTheme.typography.headlineSmall)
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
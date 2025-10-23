package com.losmoviles.features.home.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val PortalGreen = Color(0xFF97CE4C)
private val RickCyan = Color(0xFF00B5CC)
private val MortyYellow = Color(0xFFF9D648)

@Composable
fun CategoryRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        listOf(
            "Humans" to MortyYellow,
            "Aliens" to RickCyan,
            "Robots" to PortalGreen,
            "More" to Color(0xFFD9D9D9)
        ).forEach { (label, color) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(color)
                )
                Spacer(Modifier.height(6.dp))
                Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

package com.losmoviles.features.favorites.ui.composables

import org.jetbrains.compose.ui.tooling.preview.Preview


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class FavCardVM(
    val name: String,
    val subtitle: String, // ubicación / status
    val color: Color
)

@Composable
fun FavCard(vm: FavCardVM) {
    ElevatedCard(
        shape = RoundedCornerShape(18.dp),
        onClick = { /* no-op (visual) */ }
    ) {
        // Placeholder “imagen”
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    Brush.linearGradient(listOf(vm.color, vm.color.copy(alpha = 0.6f)))
                )
        ) {
            // Corazón fijo (estás en favoritos)
            Surface(
                color = Color.White.copy(alpha = 0.9f),
                shape = CircleShape,
                tonalElevation = 1.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }

        Column(Modifier.padding(12.dp)) {
            Text(text = vm.name, style = MaterialTheme.typography.titleSmall, maxLines = 1)
            Spacer(Modifier.height(2.dp))
            Text(
                text = vm.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavCardPreview() {
    MaterialTheme {
        FavCard(
            FavCardVM("Rick Sanchez", "Earth (C-137)", Color(0xFF97CE4C))
        )
    }
}

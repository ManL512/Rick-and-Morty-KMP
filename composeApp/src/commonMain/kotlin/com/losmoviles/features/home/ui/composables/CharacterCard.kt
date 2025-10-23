package com.losmoviles.features.home.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

data class CardVM(val name: String, val price: String, val color: Color, val liked: Boolean)

@Composable
fun CharacterCard(vm: CardVM) {
    ElevatedCard(shape = RoundedCornerShape(18.dp), onClick = {}) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(vm.color)
        ) {
            Surface(
                color = Color.White.copy(alpha = 0.85f),
                shape = CircleShape,
                tonalElevation = 1.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    if (vm.liked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    null,
                    tint = if (vm.liked) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
        Column(Modifier.padding(12.dp)) {
            Text(vm.name, style = MaterialTheme.typography.titleSmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text("New-York, USA", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(4.dp))
            Text(vm.price, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold))
        }
    }
}

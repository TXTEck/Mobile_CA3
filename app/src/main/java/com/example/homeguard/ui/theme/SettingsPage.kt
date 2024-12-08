package com.example.homeguard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsPage() {
    BoxWithConstraints {
        val isCompact = maxWidth < 600.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = if (isCompact) 16.dp else 32.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page Header
            Text(
                text = "HomeGuard",
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = if (isCompact) 24.sp else 32.sp),
                color = Color.White,
                modifier = Modifier.padding(bottom = if (isCompact) 16.dp else 24.dp)
            )

            // Settings Items
            SettingsItem(
                label = "Name",
                value = "John Apple",
                onClick = { /* Navigate or handle click */ }
            )

            SettingsItem(
                label = "Address",
                value = "703 N 94th St, Kansas City,\nKS 66112, USA",
                onClick = { /* Navigate or handle click */ }
            )

            SettingsItem(
                label = "Select Voice Changer",
                value = "Adult Male Voice",
                onClick = { /* Navigate or handle click */ }
            )
        }
    }
}

@Composable
fun SettingsItem(label: String, value: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Icon",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

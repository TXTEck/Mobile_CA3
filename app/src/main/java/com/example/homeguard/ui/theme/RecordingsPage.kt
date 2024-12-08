package com.example.homeguard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

data class Recording(
    val thumbnailUrl: String,
    val date: String,
    val time: String
)

@Composable
fun RecordingsPage(navController: NavController) {
    val recordings = listOf(
        Recording(
            thumbnailUrl = "https://via.placeholder.com/400",
            date = "16/10/2024",
            time = "18:24:37"
        ),
        Recording(
            thumbnailUrl = "https://via.placeholder.com/400",
            date = "17/10/2024",
            time = "21:43:52"
        ),
        Recording(
            thumbnailUrl = "https://via.placeholder.com/400",
            date = "18/10/2024",
            time = "23:17:48"
        )
    )

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

            // Scrollable List of Recordings
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(if (isCompact) 12.dp else 24.dp)
            ) {
                items(recordings) { recording ->
                    RecordingCard(recording, isCompact,navController)
                }
            }
        }
    }
}

@Composable
fun RecordingCard(recording: Recording, isCompact: Boolean,navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isCompact) 120.dp else 160.dp),
        shape = RoundedCornerShape(if (isCompact) 16.dp else 24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Thumbnail Image
            Image(
                painter = rememberAsyncImagePainter(recording.thumbnailUrl),
                contentDescription = "Recording Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isCompact) 80.dp else 120.dp)
                    .align(Alignment.TopCenter)
            )

            // Time
            Text(
                text = recording.time,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = if (isCompact) 12.sp else 16.sp),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )

            // Date
            Text(
                text = recording.date,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = if (isCompact) 12.sp else 16.sp),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )

            // Play Icon (Top-Left)
            Icon(
                imageVector = Icons.Default.PlayCircleOutline,
                contentDescription = "Play Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(if (isCompact) 24.dp else 36.dp)
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }
    }
}



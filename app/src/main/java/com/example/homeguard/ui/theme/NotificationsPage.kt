package com.example.homeguard.ui.theme

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class NotificationItem(
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val time: String,
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NotificationsPage(navController: NavController) {
    val notifications = listOf(
        NotificationItem(
            title = "Motion",
            subtitle = "Front door",
            imageUrl = "https://via.placeholder.com/400",
            time = "9:42",
        ),
        NotificationItem(
            title = "Someone is here",
            subtitle = "Front door",
            imageUrl = "https://via.placeholder.com/400",
            time = "10:12",
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
            // Header
            Text(
                text = "HomeGuard",
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = if (isCompact) 24.sp else 32.sp),
                color = Color.White,
                modifier = Modifier.padding(bottom = if (isCompact) 16.dp else 24.dp)
            )

            // Notifications List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(if (isCompact) 12.dp else 24.dp)
            ) {
                items(notifications) { notification ->
                    NotificationCard(notification, isCompact)
                }
            }
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem, isCompact: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isCompact) 100.dp else 150.dp),
        shape = RoundedCornerShape(if (isCompact) 16.dp else 24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Icon
            Box(
                modifier = Modifier
                    .size(if (isCompact) 40.dp else 60.dp)
                    .background(Color.Gray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for notification-specific icon
            }

            Spacer(modifier = Modifier.width(if (isCompact) 12.dp else 16.dp))

            // Title and Subtitle
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = notification.title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = if (isCompact) 16.sp else 20.sp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(if (isCompact) 4.dp else 8.dp))
                Text(
                    text = notification.subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = if (isCompact) 12.sp else 16.sp),
                    color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.width(if (isCompact) 8.dp else 16.dp))

            // Image and Time
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(notification.imageUrl),
                    contentDescription = "Notification Image",
                    modifier = Modifier
                        .size(if (isCompact) 40.dp else 60.dp)
                        .background(Color.Black, shape = RoundedCornerShape(if (isCompact) 8.dp else 12.dp))
                )
                Spacer(modifier = Modifier.height(if (isCompact) 4.dp else 8.dp))
                Text(
                    text = notification.time,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = if (isCompact) 12.sp else 16.sp),
                    color = Color.LightGray
                )
            }
        }
    }
}

package com.example.homeguard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Mic
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomePage(navController: NavController) {
    BoxWithConstraints {
        val isCompact = maxWidth < 600.dp

        if (isCompact) {
            CompactHomePage(navController)
        } else {
            ExpandedHomePage(navController)
        }
    }
}

@Composable
fun CompactHomePage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        CameraFeed()
        MicrophoneSection()
        PreRecordedMessages(navController)
    }
}

@Composable
fun ExpandedHomePage(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            CameraFeed()
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MicrophoneSection()
            PreRecordedMessages(navController)
        }
    }
}

@Composable
fun Header() {
    Text(
        text = "HomeGuard",
        style = MaterialTheme.typography.headlineSmall,
        color = Color.White,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun CameraFeed() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter("https://via.placeholder.com/400"),
                contentDescription = "Camera Feed",
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = "12:45",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
            Text(
                text = "CAM 1",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun MicrophoneSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.Gray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Microphone",
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Voice Changer",
            color = Color.White,
            fontSize = 18.sp
        )
        Switch(
            checked = false,
            onCheckedChange = {},
            colors = SwitchDefaults.colors(checkedThumbColor = Color.Green)
        )
    }
}

@Composable
fun PreRecordedMessages(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ChatBubbleOutline,
            contentDescription = "Messages",
            tint = Color.Yellow,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        ClickableText(
            text = AnnotatedString("Pre-Recorded Messages"),
            onClick = { navController.navigate("recordings") },
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Cyan)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun HomePagePreview() {
    // Use a dummy NavController for preview purposes
    HomePage(navController = androidx.navigation.compose.rememberNavController())
}

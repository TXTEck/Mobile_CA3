package com.example.homeguard.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun Navbar(navController: NavController) {
    val items = listOf("home", "camera", "notifications", "settings")

    NavigationBar(
        containerColor = Color.DarkGray,
        tonalElevation = 8.dp
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    when (item) {
                        "home" -> Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White)
                        "camera" -> Icon(Icons.Default.Videocam, contentDescription = "Camera", tint = Color.White)
                        "notifications" -> Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.White)
                        "settings" -> Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                    }
                },
                selected = currentRoute == item,
                onClick = {
                    navController.navigate(item) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Yellow,
                    unselectedIconColor = Color.White
                )
            )
        }
    }
}

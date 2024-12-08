package com.example.homeguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homeguard.ui.*
import com.example.homeguard.ui.theme.NotificationsPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeGuardApp()
        }
    }
}

@Composable
fun HomeGuardApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { Navbar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomePage() }
            composable("camera") { RecordingsPage() }
            composable("notifications") { NotificationsPage() }
            composable("settings") { SettingsPage() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeGuardAppPreview() {
    HomeGuardApp()
}

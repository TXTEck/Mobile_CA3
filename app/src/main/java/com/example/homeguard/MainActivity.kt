package com.example.homeguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.example.homeguard.ui.*
import com.example.homeguard.ui.theme.NotificationsPage
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeGuardApp()
        }
    }
}

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun HomeGuardApp() {
    val navController = rememberAnimatedNavController()

    Scaffold(
        bottomBar = {
            Navbar(navController = navController)
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
            enterTransition = { slideInHorizontally() },
            exitTransition = { slideOutHorizontally() },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -300 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 300 }) }
        ) {
            composable("home") { HomePage(navController) }
            composable("settings") { SettingsPage(navController) }
            composable("notifications") { NotificationsPage(navController) }
            composable("recordings") { RecordingsPage(navController) }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeGuardAppPreview() {
    HomeGuardApp()
}



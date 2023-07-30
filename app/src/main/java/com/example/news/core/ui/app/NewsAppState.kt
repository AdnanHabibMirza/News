package com.example.news.core.ui.app

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberNewsAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): NewsAppState {
    return remember(navController, windowSizeClass) {
        NewsAppState(navController, windowSizeClass)
    }
}

@Stable
class NewsAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass,
)
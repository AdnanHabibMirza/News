package com.example.news.core.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberNewsAppState(
    navController: NavHostController = rememberNavController(),
): NewsAppState {
    return remember(navController) {
        NewsAppState(navController)
    }
}

@Stable
class NewsAppState(
    val navController: NavHostController,
)
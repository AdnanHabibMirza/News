package com.example.news.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.news.core.ui.app.NewsAppState
import com.example.news.feature.article.articleScreen
import com.example.news.feature.article.navigateToArticle
import com.example.news.feature.home.homeNavigationRoute
import com.example.news.feature.home.homeScreen

@Composable
fun NewsNavHost(
    appState: NewsAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen { article ->
            navController.navigateToArticle(article)
        }
        articleScreen(appState.windowSizeClass){
            navController.popBackStack()
        }
    }
}
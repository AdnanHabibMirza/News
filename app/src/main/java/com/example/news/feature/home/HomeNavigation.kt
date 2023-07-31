package com.example.news.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.news.core.model.Article

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(onArticleClicked: (article:Article) -> Unit) {
    composable(
        route = homeNavigationRoute,
    ) {
        HomeRoute(onArticleClicked = onArticleClicked)
    }
}
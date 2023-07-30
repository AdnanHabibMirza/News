package com.example.news.feature.article

import android.net.Uri
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.news.core.model.Article

internal const val articleTitleArg = "articleTitle"
internal const val articleDescriptionArg = "articleDescription"
internal const val articleContentArg = "articleContent"
internal const val articleUrlToImageArg = "articleUrlToImage"

internal class ArticleArgs(
    val title: String,
    val description: String,
    val content: String,
    val urlToImage: String
) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                checkNotNull(savedStateHandle[articleTitleArg]),
                checkNotNull(savedStateHandle[articleDescriptionArg]),
                checkNotNull(savedStateHandle[articleContentArg]),
                checkNotNull(savedStateHandle[articleUrlToImageArg]),
            )
}

fun NavController.navigateToArticle(
    article: Article,
    navOptions: NavOptions? = null
) {
    val encodedTitle = Uri.encode(article.title)
    val encodedDescription = Uri.encode(article.description)
    val encodedContent = Uri.encode(article.content)
    val encodedUrlToImage = Uri.encode(article.urlToImage)
    this.navigate(
        route = "article_route/${encodedTitle}&${encodedDescription}&${encodedContent}&${encodedUrlToImage}",
        navOptions = navOptions
    )
}

fun NavGraphBuilder.articleScreen(
    windowSizeClass: WindowSizeClass,
    onBackClicked: () -> Unit,
) {
    composable(
        route = "article_route/{$articleTitleArg}&{$articleDescriptionArg}&{$articleContentArg}&{$articleUrlToImageArg}",
        arguments = listOf(
            navArgument(articleTitleArg) { type = NavType.StringType },
            navArgument(articleDescriptionArg) { type = NavType.StringType },
            navArgument(articleContentArg) { type = NavType.StringType },
            navArgument(articleUrlToImageArg) { type = NavType.StringType },
        ),
    ) {
        ArticleRoute(
            windowSizeClass = windowSizeClass,
            onBackClicked = onBackClicked
        )
    }
}
package com.example.news.feature.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.news.R
import com.example.news.core.model.Article

@Composable
internal fun ArticleRoute(
    modifier: Modifier = Modifier,
    viewModel: ArticleViewModel = hiltViewModel(),
    windowSizeClass: WindowSizeClass,
    onBackClicked: () -> Unit
) {
    val article by viewModel.uiState.collectAsStateWithLifecycle()
    ArticleScreen(
        modifier = modifier,
        windowSizeClass = windowSizeClass,
        article = article,
        onBackClicked = onBackClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArticleScreen(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
    article: Article?,
    onBackClicked: () -> Unit
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Icon"
                    )
                }
            },
        )
        if (article != null) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {
                    ExpandedArticle(article = article)
                } else {
                    CompactArticle(article = article)
                }
            }
        }
    }
}

@Composable
private fun CompactArticle(
    article: Article
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = article.urlToImage,
            placeholder = painterResource(id = R.drawable.placeholder)
        ),
        contentDescription = "Headline Image",
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = article.description,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = article.content,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun ExpandedArticle(
    article: Article
) {
    Row(verticalAlignment = Alignment.Bottom) {
        Image(
            painter = rememberAsyncImagePainter(
                model = article.urlToImage,
                placeholder = painterResource(id = R.drawable.placeholder)
            ),
            contentDescription = "Headline Image",
            modifier = Modifier.weight(1f),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = article.description,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = article.content,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
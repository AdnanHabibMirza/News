@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.news.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.news.BuildConfig
import com.example.news.R
import com.example.news.core.model.Article

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onArticleClicked: (article: Article) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onArticleClicked = onArticleClicked
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onArticleClicked: (article: Article) -> Unit
) {
    Column(modifier = modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(text = BuildConfig.NEWS_SOURCE)
            }
        )
        when (uiState) {
            is HomeUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.message,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            is HomeUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeUiState.Success -> {
                val lazyGridState = rememberLazyGridState()
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    state = lazyGridState,
                    columns = GridCells.Adaptive(300.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(uiState.articles) { article ->
                        Article(
                            article = article,
                            onArticleClicked = onArticleClicked
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Article(
    article: Article,
    onArticleClicked: (article: Article) -> Unit
) {
    Card(
        onClick = { onArticleClicked(article) }
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(
                    model = article.urlToImage,
                    placeholder = painterResource(id = R.drawable.placeholder)
                ),
                contentDescription = "Headline Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}
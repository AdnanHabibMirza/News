package com.example.news.feature.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.news.core.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val articleArgs: ArticleArgs = ArticleArgs(savedStateHandle)

    private val _uiState = MutableStateFlow<Article?>(null)
    val uiState: StateFlow<Article?> get() = _uiState.asStateFlow()

    init {
        val article = Article(
            content = articleArgs.content,
            description = articleArgs.description,
            title = articleArgs.title,
            urlToImage = articleArgs.urlToImage
        )
        _uiState.tryEmit(article)
    }
}
package com.example.news.feature.home

import com.example.news.core.model.Article

sealed interface HomeUiState {
    data class Success(val articles: List<Article>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}
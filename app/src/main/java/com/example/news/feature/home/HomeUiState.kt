package com.example.news.feature.home

import com.example.news.core.model.Article

sealed interface HomeUiState {
    data class Success(val articles: List<Article>) : HomeUiState
    data class Error(val message:String) : HomeUiState
    object Loading : HomeUiState
}
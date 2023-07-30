package com.example.news.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.BuildConfig
import com.example.news.core.common.result.Result
import com.example.news.core.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> get() = _uiState.asStateFlow()

    init {
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            newsRepository.getTopHeadlines(BuildConfig.NEWS_SOURCE).collectLatest {
                _uiState.tryEmit(
                    when (it) {
                        is Result.Error -> HomeUiState.Error(it.exception?.message ?: "Oops!")
                        is Result.Loading -> HomeUiState.Loading
                        is Result.Success -> HomeUiState.Success(it.data)
                    }
                )
            }
        }
    }
}
package com.example.news

import com.example.news.core.common.result.Result
import com.example.news.core.data.ext.asArticle
import com.example.news.core.data.repository.NewsRepository
import com.example.news.core.network.model.ArticleNetwork
import com.example.news.feature.home.HomeUiState
import com.example.news.feature.home.HomeViewModel
import com.example.news.utils.MainDispatcherTest
import io.mockk.awaits
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : MainDispatcherTest() {

    private lateinit var newsRepository: NewsRepository
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        newsRepository = mockk()
        coEvery { newsRepository.getTopHeadlines(any()) } just awaits
        viewModel = HomeViewModel(newsRepository)
    }

    @Test
    fun `test fetchTopHeadlines success`() = runTest {
        val testData = TestConstants.newsNetwork.articles.map(ArticleNetwork::asArticle)
        coEvery { newsRepository.getTopHeadlines(HomeViewModel.NEWS_SOURCE) } returns flowOf(
            Result.Success(
                testData
            )
        )

        viewModel.fetchTopHeadlines()

        val uiState = viewModel.uiState.value
        assertEquals(uiState, HomeUiState.Success(testData))
    }

    @Test
    fun `test fetchTopHeadlines error`() = runTest {
        coEvery { newsRepository.getTopHeadlines(HomeViewModel.NEWS_SOURCE) } returns flowOf(
            Result.Error(
                Exception("Error fetching headlines")
            )
        )

        viewModel.fetchTopHeadlines()

        val uiState = viewModel.uiState.value
        assertEquals(uiState, HomeUiState.Error)
    }

    @Test
    fun `test fetchTopHeadlines loading`() = runTest {
        coEvery { newsRepository.getTopHeadlines(HomeViewModel.NEWS_SOURCE) } returns flowOf(
            Result.Loading
        )

        viewModel.fetchTopHeadlines()

        val uiState = viewModel.uiState.value
        assertEquals(uiState, HomeUiState.Loading)
    }
}
package com.example.news

import androidx.lifecycle.SavedStateHandle
import com.example.news.core.common.decoder.StringDecoder
import com.example.news.core.data.ext.asArticle
import com.example.news.feature.article.ArticleViewModel
import com.example.news.feature.article.articleContentArg
import com.example.news.feature.article.articleDescriptionArg
import com.example.news.feature.article.articleTitleArg
import com.example.news.feature.article.articleUrlToImageArg
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleViewModelTest {

    private lateinit var viewModel: ArticleViewModel
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var stringDecoder: StringDecoder

    @Before
    fun setup() {
        savedStateHandle = mockk()
        stringDecoder = mockk()
    }

    @Test
    fun `ViewModel initializes with correct Article`() = runTest {
        val testData = TestConstants.newsNetwork.articles.first().asArticle()

        every { stringDecoder.decodeString(any()) } answers { firstArg() }

        every { savedStateHandle.get<String>(articleTitleArg) } returns testData.title
        every { savedStateHandle.get<String>(articleDescriptionArg) } returns testData.description
        every { savedStateHandle.get<String>(articleContentArg) } returns testData.content
        every { savedStateHandle.get<String>(articleUrlToImageArg) } returns testData.urlToImage

        viewModel = ArticleViewModel(savedStateHandle, stringDecoder)

        val uiState = viewModel.uiState.value

        verify(exactly = 4) { stringDecoder.decodeString(any()) }

        assertEquals(uiState, testData)
    }
}
package com.example.news.core.data.repository

import com.example.news.core.data.ext.asArticle
import com.example.news.core.model.Article
import com.example.news.core.network.api.NewsApi
import javax.inject.Inject

class DefaultNewsRepository @Inject constructor(
    private val newsApi: NewsApi
):NewsRepository {

    override suspend fun getTopHeadlines(sources: String): List<Article> {
        return newsApi.getTopHeadlines(sources).articles.map { it.asArticle() }
    }
}
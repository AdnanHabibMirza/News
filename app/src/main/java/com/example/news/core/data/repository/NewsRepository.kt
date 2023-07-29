package com.example.news.core.data.repository

import com.example.news.core.model.Article

interface NewsRepository {
    suspend fun getTopHeadlines(sources:String): List<Article>
}
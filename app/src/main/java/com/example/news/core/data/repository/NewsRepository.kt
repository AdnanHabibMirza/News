package com.example.news.core.data.repository

import com.example.news.core.common.result.Result
import com.example.news.core.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getTopHeadlines(sources:String): Flow<Result<List<Article>>>
}
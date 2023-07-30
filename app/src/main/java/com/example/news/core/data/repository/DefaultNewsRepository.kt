package com.example.news.core.data.repository

import com.example.news.core.common.dispatcher.Dispatcher
import com.example.news.core.common.dispatcher.NewsDispatchers
import com.example.news.core.common.result.Result
import com.example.news.core.common.result.asResult
import com.example.news.core.data.ext.asArticle
import com.example.news.core.model.Article
import com.example.news.core.network.api.NewsApi
import com.example.news.core.network.model.ArticleNetwork
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DefaultNewsRepository @Inject constructor(
    @Dispatcher(NewsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val newsApi: NewsApi,
) : NewsRepository {

    override suspend fun getTopHeadlines(sources: String): Flow<Result<List<Article>>> {
        return flow {
            val articles = newsApi.getTopHeadlines(sources).articles.map(ArticleNetwork::asArticle)
            emit(articles)
        }.asResult().flowOn(ioDispatcher)
    }
}
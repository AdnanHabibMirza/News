package com.example.news.core.network.api

import com.example.news.core.network.model.NewsNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines/")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String
    ): NewsNetwork
}
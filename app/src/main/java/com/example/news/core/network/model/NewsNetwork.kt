package com.example.news.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsNetwork(
    @Json(name = "articles")
    val articles: List<ArticleNetwork>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)
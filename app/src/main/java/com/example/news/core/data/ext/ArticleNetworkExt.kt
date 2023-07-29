package com.example.news.core.data.ext

import com.example.news.core.model.Article
import com.example.news.core.network.model.ArticleNetwork

fun ArticleNetwork.asArticle() = Article(
    content = content,
    description = description,
    title = title,
    urlToImage = urlToImage
)
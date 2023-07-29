package com.example.news

import com.example.news.core.network.model.ArticleNetwork
import com.example.news.core.network.model.NewsNetwork
import com.example.news.core.network.model.SourceNetwork

object TestConstants {
    val newsNetwork = NewsNetwork(
        status = "ok",
        totalResults = 10,
        articles = listOf(
            ArticleNetwork(
                author = "BBC News",
                content = "Chief heat officers are a new and rare phenomenon...",
                description = "Experts say the US is woefully unprepared for record heat, but three cities are leading the way.",
                publishedAt = "2023-07-28T12:52:21.6381903Z",
                source = SourceNetwork(id = "bbc-news", name = "BBC News"),
                title = "On the frontline in battle against searing heat",
                url = "http://www.bbc.co.uk/news/world-us-canada-66272154",
                urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/8EC1/production/_130554563_gettyimages-1549173068.jpg"
            ),
            ArticleNetwork(
                author = "BBC News",
                content = "A man in the US has caught the moment three whales breached...",
                description = "A man in the US has filmed three humpback whales breaching in almost perfect synchronisation off the coast of Massachusetts.",
                publishedAt = "2023-07-28T12:22:21.3419137Z",
                source = SourceNetwork(id = "bbc-news", name = "BBC News"),
                title = "Watch ‘epic’ moment three whales jump in unison",
                url = "http://www.bbc.co.uk/news/world-us-canada-66333690",
                urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/117F1/production/_130556617_p0g3d9xl.jpg"
            ),
            ArticleNetwork(
                author = "BBC News",
                content = "General Abdourahmane Tchiani has declared himself the new leader of Niger...",
                description = "General Abdourahmane Tchiani is addressing the nation after staging a coup.",
                publishedAt = "2023-07-28T11:37:21.9672452Z",
                source = SourceNetwork(id = "bbc-news", name = "BBC News"),
                title = "Niger coup: General Tchiani declares himself leader",
                url = "http://www.bbc.co.uk/news/world-africa-66337767",
                urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/13FF3/production/_112170918_breaking-promo-v20e2-red-976x549.png"
            )
        )
    )
}
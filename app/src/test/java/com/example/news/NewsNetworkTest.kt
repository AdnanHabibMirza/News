package com.example.news

import com.example.news.core.network.model.NewsNetwork
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStreamReader
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NewsNetworkTest {

    @Test
    fun `newsNetwork json should map to NewsNetwork object`() {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val newsNetworkJsonAdapter = moshi.adapter(NewsNetwork::class.java)

        javaClass.classLoader?.getResourceAsStream("newsNetwork.json")
            .use { inputStream ->
                val json = InputStreamReader(inputStream).use { it.readText() }
                val newsNetwork = newsNetworkJsonAdapter.fromJson(json)
                assertEquals(TestConstants.newsNetwork, newsNetwork)
            }
    }
}

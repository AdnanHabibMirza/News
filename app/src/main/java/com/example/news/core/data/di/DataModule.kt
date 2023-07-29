package com.example.news.core.data.di

import com.example.news.core.data.repository.DefaultNewsRepository
import com.example.news.core.data.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNewsRepository(defaultNewsRepository: DefaultNewsRepository): NewsRepository
}

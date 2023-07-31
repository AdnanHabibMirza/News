package com.example.news.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class MainDispatcherTest(
    dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule(dispatcher)
}
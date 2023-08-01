package com.example.news

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.news.biometric.BiometricUtils.isBiometricAvailable
import com.example.news.biometric.BiometricUtils.showBiometricPrompt
import com.example.news.core.ui.app.NewsApp
import com.example.news.core.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                NewsApp(calculateWindowSizeClass(activity = this))
            }
        }

        installSplashScreen().setKeepOnScreenCondition {
            isBiometricAvailable() && viewModel.isAuthenticated.value.not()
        }

        if (isBiometricAvailable() && viewModel.isAuthenticated.value.not()) {
            showBiometricPrompt {
                viewModel.authenticate()
            }
        }
    }
}
package com.example.news.biometric

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

object BiometricUtils {
    private const val TAG = "BiometricUtils"
    private const val AUTHENTICATORS = BIOMETRIC_STRONG or BIOMETRIC_WEAK

    private val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .apply {
            setTitle("Login")
            setNegativeButtonText("cancel")
            setConfirmationRequired(false)
            setAllowedAuthenticators(AUTHENTICATORS)
        }.build()

    fun Activity.isBiometricAvailable(): Boolean {
        val canAuthenticate = BiometricManager.from(this).canAuthenticate(AUTHENTICATORS)
        return canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS
    }

    fun AppCompatActivity.showBiometricPrompt(
        processSuccess: (BiometricPrompt.AuthenticationResult?) -> Unit
    ) {
        val prompt = BiometricPrompt(
            this,
            ContextCompat.getMainExecutor(this),
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errCode, errString)
                    Log.d(TAG, "$errCode: $errString")
                    processSuccess(null) // FIXME: bypassing for testing
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Log.d(TAG, "Biometric not recognized.")
                    processSuccess(null) // FIXME: bypassing for testing
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Log.d(TAG, "Authentication was successful")
                    processSuccess(result)
                }
            }
        )
        prompt.authenticate(promptInfo)
    }
}
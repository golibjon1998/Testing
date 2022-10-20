package com.example.testing

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_biometric.*
import java.util.concurrent.Executor

class BiometricActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promtInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric)

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this@BiometricActivity, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    splashTv.text = "AuthenticationError : $errString"
                    Toast.makeText(
                        this@BiometricActivity,
                        "Authentication error",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    splashTv.text = "Authentication Success!"
                    Toast.makeText(
                        this@BiometricActivity,
                        "Authentication success",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this@BiometricActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()


                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    splashTv.text = "Authentication failed"
                    Toast.makeText(
                        this@BiometricActivity,
                        "Authentication failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        promtInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric authentication")
            .setSubtitle("Enter using fingerprint")
            .setNegativeButtonText("Use app password")
            .build()


        biometricPrompt.authenticate(promtInfo)
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}
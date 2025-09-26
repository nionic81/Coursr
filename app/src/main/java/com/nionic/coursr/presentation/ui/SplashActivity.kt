package com.nionic.coursr.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nionic.coursr.data.local.AuthManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val authManager: AuthManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val email = authManager.userEmail.first()
            val targetActivity =
                if (email != null) {
                    MainActivity::class.java
                } else {
                    LoginActivity::class.java
                }
            startActivity(Intent(this@SplashActivity, targetActivity))
            finish()
        }

    }
}
package com.nionic.coursr.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nionic.coursr.data.local.AuthManager
import com.nionic.coursr.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null
    private val authManager: AuthManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btLogin?.setOnClickListener {
            val email = binding?.etEnterEmail?.text.toString().trim()
            if (email.isValidEmail()) {
                lifecycleScope.launch {
                    authManager.saveEmail(email)
                    val intent =
                        Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun String.isValidEmail(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}
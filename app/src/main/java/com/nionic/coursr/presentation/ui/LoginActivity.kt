package com.nionic.coursr.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.nionic.coursr.OK_URL
import com.nionic.coursr.R
import com.nionic.coursr.RUS_LETTERS
import com.nionic.coursr.VK_URL
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

        binding?.etEnterEmail?.filters = arrayOf(getEmailFilter())
        binding?.etEnterEmail?.addTextChangedListener(getTextWatcher())
        binding?.etEnterPassword?.addTextChangedListener(getTextWatcher())
        binding?.btLogin?.setOnClickListener { login() }
        binding?.btVK?.setOnClickListener { linkIntent(VK_URL) }
        binding?.btOK?.setOnClickListener { linkIntent(OK_URL) }
        updateLoginButtonState()
    }

    private fun getTextWatcher(): TextWatcher {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                updateLoginButtonState()
            }

            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {

            }

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {

            }
        }
        return textWatcher
    }

    private fun getEmailFilter(): InputFilter {
        val emailFilter = InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                val c = source[i]
                if (c in RUS_LETTERS) return@InputFilter ""
                if (c.isLetter()) {
                    if ((c !in 'a'..'z') && (c !in 'A'..'Z')) return@InputFilter ""
                } else if (!c.isDigit() && c != '@' && c != '.') return@InputFilter ""
            }
            null
        }
        return emailFilter
    }

    private fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,}$"))
    }

    private fun login() {
        val email = binding?.etEnterEmail?.text.toString().trim()
        val password = binding?.etEnterPassword?.text.toString()
        if (!isValidEmail(email)) {
            binding?.etEnterEmail?.error = getString(R.string.email_is_incorrect)
            return
        }
        if (password.length < 6) {
            binding?.etEnterPassword?.error = getString(R.string.password_is_over_six)
            return
        }
        lifecycleScope.launch {
            authManager.saveCredential(email, password)
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun updateLoginButtonState() {
        val isEmailValid = isValidEmail(binding?.etEnterEmail?.text.toString().trim())
        val isPasswordValid = binding?.etEnterPassword?.text.toString().length >= 6
        binding?.btLogin?.isEnabled = isEmailValid && isPasswordValid
        binding?.btLogin?.alpha = if (binding?.btLogin?.isEnabled == true) 1.0f else 0.5f
    }

    private fun linkIntent(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}
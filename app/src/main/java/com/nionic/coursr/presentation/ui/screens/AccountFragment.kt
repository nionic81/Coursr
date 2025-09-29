package com.nionic.coursr.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nionic.coursr.R
import com.nionic.coursr.data.local.AuthManager
import com.nionic.coursr.databinding.FragmentAccountBinding
import com.nionic.coursr.presentation.ui.MainActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AccountFragment : Fragment() {

    private var binding: FragmentAccountBinding? = null
    private val authManager: AuthManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)

        lifecycleScope.launch {
            val savedEmail = authManager.userEmail.first()
            binding?.tvEmailTitle?.text = getString(R.string.user_email, savedEmail)
        }
        binding?.btLogout?.setOnClickListener {
            (activity as MainActivity).logOut()
        }

        return binding?.root
    }
}
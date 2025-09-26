package com.nionic.coursr.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nionic.coursr.databinding.FragmentAccountBinding
import com.nionic.coursr.presentation.ui.MainActivity

class AccountFragment : Fragment() {

    private var binding: FragmentAccountBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)

        binding?.btLogout?.setOnClickListener {
            (activity as MainActivity).logOut()
        }

        return binding?.root
    }
}
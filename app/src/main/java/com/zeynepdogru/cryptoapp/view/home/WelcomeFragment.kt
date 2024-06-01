package com.zeynepdogru.cryptoapp.view.home

import com.zeynepdogru.cryptoapp.databinding.FragmentWelcomeBinding
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.zeynepdogru.cryptoapp.R

class WelcomeFragment:Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        binding.btnWelcome.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_welcomeFragment_to_homeFragment)
        }
        return binding.root
    }

}
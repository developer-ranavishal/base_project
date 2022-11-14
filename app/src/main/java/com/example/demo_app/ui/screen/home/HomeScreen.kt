package com.example.demo_app.ui.screen.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.demo_app.core.BaseFragment
import com.example.demo_app.databinding.FragmentHomeScreenBinding
import com.example.demo_app.ui.screen.auth.VMAuth

class HomeScreen : BaseFragment<VMAuth, FragmentHomeScreenBinding>() {

    override fun getViewBinding(): FragmentHomeScreenBinding  = FragmentHomeScreenBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }


}
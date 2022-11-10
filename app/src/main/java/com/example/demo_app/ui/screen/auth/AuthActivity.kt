package com.example.demo_app.ui.screen.auth


import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.demo_app.R
import com.example.demo_app.core.BaseActivity
import com.example.demo_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity  : BaseActivity<VMAuth, ActivityMainBinding>() {

    private lateinit var navController: NavController

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    /** this method will call in onCreate() in BaseActivity ,write all startup logic here*/
    override fun onRendered(viewModel: VMAuth, binding: ActivityMainBinding) {
        super.onRendered(viewModel, binding)
        navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)!!.findNavController()

    }

}
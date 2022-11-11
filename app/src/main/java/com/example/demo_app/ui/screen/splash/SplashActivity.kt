package com.example.demo_app.ui.screen.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demo_app.R
import com.example.demo_app.databinding.ActivitySplashBinding
import com.example.demo_app.ui.screen.auth.AuthActivity
import com.example.demo_app.utils.extensions.openActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
      showFullScreen()
        loginOrHome()

    }

    private fun loginOrHome(){
        lifecycleScope.launch{
            delay(3000)
            openActivity<AuthActivity>()
            finish()
        }

    }

    private fun showFullScreen(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

    }


}
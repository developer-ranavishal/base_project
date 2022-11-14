package com.example.demo_app.ui.screen.home


import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.demo_app.R
import com.example.demo_app.core.BaseActivity
import com.example.demo_app.databinding.ActivityHomeBinding
import com.example.demo_app.databinding.ActivityMainBinding
import com.example.demo_app.ui.screen.auth.VMAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<VMAuth, ActivityHomeBinding>() {

    private lateinit var navController: NavController

    override fun getViewBinding() =  ActivityHomeBinding.inflate(layoutInflater)

    /** this method will call in onCreate() in BaseActivity ,write all startup logic here*/
    override fun onRendered(viewModel: VMAuth, binding:  ActivityHomeBinding) {
        super.onRendered(viewModel, binding)
        navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)!!.findNavController()

    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController, binding.drawerLayout)
//    }

    private fun setupDrawerLayout() {
       // NavigationUI.setupActionBarWithNavController(this, navController,binding.drawerLayout)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
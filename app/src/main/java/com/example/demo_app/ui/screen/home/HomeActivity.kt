package com.example.demo_app.ui.screen.home


import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.demo_app.R
import com.example.demo_app.core.BaseActivity
import com.example.demo_app.databinding.ActivityHomeBinding
import com.example.demo_app.databinding.ActivityMainBinding
import com.example.demo_app.ui.screen.auth.AuthActivity
import com.example.demo_app.ui.screen.auth.VMAuth
import com.example.demo_app.utils.extensions.openActivity
import com.example.demo_app.utils.extensions.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<VMAuth, ActivityHomeBinding>() {

    private lateinit var navController: NavController

    override fun getViewBinding() =  ActivityHomeBinding.inflate(layoutInflater)

    /** this method will call in onCreate() in BaseActivity ,write all startup logic here*/
    override fun onRendered(viewModel: VMAuth, binding:  ActivityHomeBinding) {
        super.onRendered(viewModel, binding)
        navController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)!!.findNavController()

     onNavigationViewItemSelected()
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


    private fun onNavigationViewItemSelected(){

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item_home -> showToast("Home Selected!")
                R.id.item_logout -> {
                    makeDialog()

                }
            }
            return@setNavigationItemSelectedListener true
        }
    }




    private fun makeDialog(){
        MaterialAlertDialogBuilder(this,R.style.MaterialAlertDialog_App)
            .setTitle(resources.getString(R.string.logout_title))
         //   .setMessage(resources.getString(R.string.supporting_text))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                // Respond to positive button press
                showToast("User Logout Successfully!")
                viewModel.firebaseAuth.signOut()
                viewModel.prefManger().put("f-auth-token","")
                openActivity<AuthActivity>()
                finish()
            }.show()
    }




}
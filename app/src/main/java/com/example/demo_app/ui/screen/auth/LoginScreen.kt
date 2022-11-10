package com.example.demo_app.ui.screen.auth


import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import app.beYou.utils.extensions.handleUrlClicks
import app.beYou.utils.extensions.snack
import com.example.demo_app.R
import com.example.demo_app.core.BaseFragment
import com.example.demo_app.databinding.FragmentLoginScreenBinding
import com.example.demo_app.utils.extensions.isFragmentInBackStack
import com.example.demo_app.utils.extensions.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule


class LoginScreen  : BaseFragment<VMAuth, FragmentLoginScreenBinding>() {
    lateinit var navController: NavController
    override fun getViewBinding(): FragmentLoginScreenBinding =
        FragmentLoginScreenBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.apply {
            controller = this@LoginScreen
            lifecycleOwner = viewLifecycleOwner
            binding.viewModel = vm
        }

        handleHyperLink()
    }


    /** hit login api here */
    fun login() {
        loader.show()
        if (vm.loginValidation()) {
            lifecycleScope.launch {
                 delay(4000)
                loader.hide()
                binding.root.snack("Login Success!") {}
                vm.resetLoginForm()
            }
        } else {
            lifecycleScope.launch {
                delay(1000)
                loader.hide()
                binding.root.snack("InValid Form") {}

            }
        }
    }


    /** method call on choose email or mobile for login **/
    fun onChangeType() {
        vm.isAuthTypeEmail.value = !vm.isAuthTypeEmail.value!!
    }



    /** goto Signup **/
    private fun handleHyperLink() {
        binding.tvLogin.handleUrlClicks {
            if (isFragmentInBackStack(R.id.signUpFragment)) {
                activity?.onBackPressed()
            } else {
                navController.navigate(R.id.action_loginScreen_to_signUpFragment)
            }
        }

    }


    override fun onStop() {
        super.onStop()
        vm.resetLoginForm()
    }






}






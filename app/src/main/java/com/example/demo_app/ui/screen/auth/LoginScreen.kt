package com.example.demo_app.ui.screen.auth


import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import app.beYou.utils.extensions.handleUrlClicks
import app.beYou.utils.extensions.snack
import com.example.demo_app.R
import com.example.demo_app.core.BaseFragment
import com.example.demo_app.databinding.FragmentLoginScreenBinding
import com.example.demo_app.logD
import com.example.demo_app.ui.screen.home.HomeActivity
import com.example.demo_app.utils.extensions.isFragmentInBackStack
import com.example.demo_app.utils.extensions.openActivity
import com.example.demo_app.utils.extensions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        if (vm.loginValidation()) {
            lifecycleScope.launch(Dispatchers.Main) {
                loginUser(vm.email.value!!, vm.password.value!!)
            }

        }
        else binding.root.snack("InValid Form") {}
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


    /** function to login Account*/
    private suspend fun loginUser(email: String, password: String) {
        loader.show()
        withContext(Dispatchers.IO){
            vm.firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {            //  Sign in success, update UI with the signed-in user's information
                        loader.hide()
                        goToHome(vm.firebaseAuth.currentUser?.uid)

                    } else {
                        // If sign in fails, display a message to the user.
                        loader.hide()
                        logD("login", "Login failed of $email")
                        logD("login", "${task.exception}")
                        binding.root.snack(task.exception?.localizedMessage ?: "Login Failed") { }



                    }

                }

        }

    }


    /** manage session here and after goto home **/
    private fun goToHome(token : String?){
        vm.prefManger().put("f-auth-token",token as String)
        binding.root.snack("Login success!") {}
        requireActivity().openActivity<HomeActivity>()
        vm.resetLoginForm()
        requireActivity().finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        vm.resetLoginForm()
    }


}









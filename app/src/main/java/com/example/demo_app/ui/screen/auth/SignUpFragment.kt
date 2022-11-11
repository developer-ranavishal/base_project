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
import com.example.demo_app.databinding.FragmentSignUpBinding
import com.example.demo_app.logD
import com.example.demo_app.utils.extensions.isFragmentInBackStack
import kotlinx.coroutines.*
import java.util.*

class SignUpFragment : BaseFragment<VMAuth,FragmentSignUpBinding>() {
    lateinit var navController: NavController
    override fun getViewBinding(): FragmentSignUpBinding = FragmentSignUpBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)


        binding.apply {
            controller = this@SignUpFragment
            lifecycleOwner = viewLifecycleOwner
            viewModel = vm
        }
        handleHyperLink()

    }

    /** register the new user here */
    fun createAccount() {
        if (vm.signUpValidation()) {
           lifecycleScope.launch(Dispatchers.Main){
                createUser(vm.email.value!!, vm.password.value!!)
            }
        }
        else binding.root.snack("InValid Form") {}
    }




    /** method call on choose email or mobile for login **/
    fun onChangeType() {
        vm.isAuthTypeEmail.value = !vm.isAuthTypeEmail.value!!
    }



    /** handle link to goto loginScreen */
    private fun handleHyperLink() {
        binding.tvLogin.handleUrlClicks {
            if (isFragmentInBackStack(R.id.signUpFragment)) {
                activity?.onBackPressed()
            } else {
                navController.navigate(R.id.action_loginScreen_to_signUpFragment)
            }
        }

    }


    /** function to create Account*/
    private suspend fun createUser(email: String, password: String) {
        loader.show()
        withContext(Dispatchers.IO){
            vm.firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        loader.hide()
                        logD("register", "Registration success of $email")
                        val user = vm.firebaseAuth.currentUser
                        logD("register", "current user $user")
                        binding.root.snack("Registration success!") {}
                        vm.resetSignUpForm()

                    } else {
                        // If sign in fails, display a message to the user.
                        loader.hide()
                        logD("register", "Registration failed of $email")
                        logD("register", "${task.exception}")
                        binding.root.snack(task.exception?.localizedMessage ?: "Signup Failed") { }
                    }
                }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        vm.resetSignUpForm()
    }







}







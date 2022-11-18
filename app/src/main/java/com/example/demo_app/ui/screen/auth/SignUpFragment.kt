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
import com.example.demo_app.data.local.UserProfile
import com.example.demo_app.databinding.FragmentSignUpBinding
import com.example.demo_app.logD
import com.example.demo_app.ui.screen.home.HomeActivity
import com.example.demo_app.utils.extensions.getUserProfile
import com.example.demo_app.utils.extensions.isFragmentInBackStack
import com.example.demo_app.utils.extensions.openActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.HashMap

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
                createUser()
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
    private suspend fun createUser() {
        loader.show()
        withContext(Dispatchers.IO){
            if (vm.isAuthTypeEmail.value!!){
                vm.firebaseAuth.createUserWithEmailAndPassword(vm.email.value!!, vm.password.value!!)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            logD("register","${vm.firebaseAuth.currentUser?.uid}")
                            goToHome(vm.firebaseAuth.currentUser?.uid)
                            loader.hide()
                        } else {
                            // If sign in fails, display a message to the user.
                            loader.hide()
                            logD("register", "${task.exception}")
                            binding.root.snack(task.exception?.localizedMessage ?: "Signup Failed") { }
                        }
                    }
            }

        }

    }


    /** manage session here and after goto home **/
    private fun goToHome(token : String?){
        vm.prefManger().put("f-auth-token",token as String)
        addFormToDb()  // adding data after getting token
        requireActivity().openActivity<HomeActivity>()
        vm.resetSignUpForm()
        binding.root.snack("Create User success!") {}
        requireActivity().finish()
    }




    /** add form to cloud fireStore **/
    private fun addFormToDb(){

        val userProfile = UserProfile().getUserProfile(
            firstName = vm.firstName.value!!,
            lastName =  vm.lastName.value!!, age = vm.age.value!!,
            userName = vm.username.value!!, email = vm.email.value!!,
            uid = vm.prefManger().getString("f-auth-token")
        )


        vm.db.collection("users").document(vm.prefManger().getString("f-auth-token"))
            .set(userProfile)
            .addOnSuccessListener { documentReference ->
              logD("db","document added success! $documentReference")
            }
            .addOnFailureListener { e ->
                logD("db", "Error adding document $e")

            }

    }

    override fun onDestroy() {
        super.onDestroy()
        vm.resetSignUpForm()
    }


}






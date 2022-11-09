package com.example.demo_app.ui.screen.auth


import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import app.beYou.utils.extensions.handleUrlClicks
import app.beYou.utils.extensions.snack
import com.example.demo_app.R
import com.example.demo_app.core.BaseFragment
import com.example.demo_app.databinding.FragmentSignUpBinding
import com.example.demo_app.utils.extensions.isFragmentInBackStack

class SignUpFragment : BaseFragment<VMAuth,FragmentSignUpBinding>() {
    lateinit var navController: NavController
    override fun getViewBinding(): FragmentSignUpBinding  = FragmentSignUpBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.apply {
            controller= this@SignUpFragment
            lifecycleOwner = viewLifecycleOwner
        }
        binding.viewModel = vm
        handleHyperLink()

    }

    /** hit login api here */
    fun register(){
        if (vm.signUpValidation()){
            binding.root.snack("Register User Success!"){}
            vm.resetSignUpForm()
        }
        else{
            binding.root.snack("InValid Form"){}
        }

    }




    /** method call on choose email or mobile for login **/
    fun onChangeType(){
        vm.isAuthTypeEmail.value =  !vm.isAuthTypeEmail.value!!
    }







    private fun handleHyperLink(){
        binding.tvLogin.handleUrlClicks {
            if(isFragmentInBackStack(R.id.signUpFragment)){
                activity?.onBackPressed()
            }
            else {
                navController.navigate(R.id.action_loginScreen_to_signUpFragment)
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        vm.resetSignUpForm()
    }

}




package com.example.demo_app.ui.screen.auth

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class VMAuth : ViewModel() {
    val isAuthTypeEmail = MutableLiveData(true)

    val email  = MutableLiveData("")
    val phone = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

    val firstName = MutableLiveData("")
    val lastName = MutableLiveData("")
    val username = MutableLiveData("")
    val age = MutableLiveData(0)

    val tilEmailHelper = MutableLiveData("")
    val tilPhoneHelper = MutableLiveData("")
    val tilPasswordHelper = MutableLiveData("")
    val tilFnHelper = MutableLiveData("")
    val tilLnHelper = MutableLiveData("")
    val tilUserNameHelper = MutableLiveData("")
    val tilAgeHelper = MutableLiveData("")
    val tilCmPasswordHelper = MutableLiveData("")



    var otp = MutableLiveData("")






    /** password validation **/
    private fun nameValidation(name : String) : String?{
        if (name.isNotEmpty()){
            when {
                name.length<3 -> return "Minimum 3 Character Required"
            }
        }
        else return "Name field must not empty"
        return null
    }


    /** password validation **/
    private fun userNameValidation(userName : String) : String?{
        if (userName.isNotEmpty()){
            when {
                userName.length<6 -> return "Minimum 6 Character UserName"
            }
        }
        else return "UserName field must not empty"
        return null
    }


    /** password validation **/
    private fun passwordValidation(password : String) : String?{
        if (password.isNotEmpty()){
            when {
                password.length<8 -> return "Minimum 8 Character Password"
                !password.matches(".*[A-Z].*".toRegex()) -> return "Must Contain 1 Upper-case Character"
                !password.matches(".*[a-z].*".toRegex()) -> return "Must Contain 1 Lower-case Character"
                !password.matches(".*[@#\$%^&+=].*".toRegex()) -> return "Must Contain 1 Special Character (@#\$%^&+=)"
            }
        }
        else return "Password field must not empty"
        return null
    }



    /** password validation **/
    private fun confirmPasswordValidation(confirmPassword : String,password: String) : String?{
        if (confirmPassword.isNotEmpty()){
            when {
                confirmPassword.length<8 -> return "Minimum 8 Character Password"
                !confirmPassword.matches(".*[A-Z].*".toRegex()) -> return "Must Contain 1 Upper-case Character"
                !confirmPassword.matches(".*[a-z].*".toRegex()) -> return "Must Contain 1 Lower-case Character"
                !confirmPassword.matches(".*[@#\$%^&+=].*".toRegex()) -> return "Must Contain 1 Special Character (@#\$%^&+=)"
                confirmPassword!=password -> return "password mismatch"
            }
        }
        else return "Confirm Password field must not empty"
        return null
    }


    /** email validation **/
    private fun emailValidation(target: CharSequence): String? {
        if (target.isNotEmpty()) {
            when {
                !Patterns.EMAIL_ADDRESS.matcher(target).matches() -> return "Invalid email!"

            }
        } else return "Email field must not empty"

        return null
    }




    /** phone validation **/
    private fun phoneValidation(target: CharSequence): String? {
        if (target.isNotEmpty()) {
            when {
                !Patterns.PHONE.matcher(target).matches() -> return "Invalid mobile number!"
                target.length != 10 -> return "character must be 10 in mobile"

            }
        } else return "Mobile field must not empty"

        return null

    }



    /** complete validation function!*/
    fun loginValidation() : Boolean{
        if (isAuthTypeEmail.value!!) {

            val isValidEmail = emailValidation(email.value!!.trim())
            val isValidPassword = passwordValidation(password.value!!.trim())

            tilEmailHelper.value = emailValidation(email.value!!.trim())
            tilPasswordHelper.value = passwordValidation(password.value!!.trim())

            return isValidPassword == null && isValidEmail == null

        } else {
            val isValidPassword = passwordValidation(password.value!!.trim())
            val isValidPhone = phoneValidation(phone.value!!.trim())

            tilPasswordHelper.value = isValidPassword
           tilPhoneHelper.value = isValidPhone

            return isValidPassword == null && isValidPhone == null
        }


    }




    /** complete validation function!*/
    fun signUpValidation() : Boolean{
        if (isAuthTypeEmail.value!!) {

            val isValidEmail = emailValidation(email.value!!.trim())
            val isValidPassword = passwordValidation(password.value!!.trim())
            val isValidFn = nameValidation(firstName.value!!)
            val isValidLn = nameValidation(lastName.value!!)
            val isValidUserName = userNameValidation(username.value!!)
            val isValidConfirmPassword = confirmPasswordValidation(confirmPassword.value!!,password.value!!)

            tilEmailHelper.value = isValidEmail
            tilPasswordHelper.value = isValidPassword
            tilFnHelper.value =isValidFn
            tilLnHelper.value = isValidLn
            tilUserNameHelper.value = isValidUserName
            tilCmPasswordHelper.value = isValidConfirmPassword

            return isValidPassword == null && isValidConfirmPassword==null && isValidEmail == null  && isValidFn==null && isValidLn == null && isValidUserName==null

        } else {
            val isValidPassword = passwordValidation(password.value!!.trim())
            val isValidPhone = phoneValidation(phone.value!!.trim())
            val isValidFn = nameValidation(firstName.value!!)
            val isValidLn = nameValidation(lastName.value!!)
            val isValidUserName = userNameValidation(username.value!!)
            val isValidConfirmPassword = confirmPasswordValidation(confirmPassword.value!!,password.value!!)

            tilPasswordHelper.value = isValidPassword
            tilPhoneHelper.value = isValidPhone
            tilFnHelper.value =isValidFn
            tilLnHelper.value = isValidLn
            tilUserNameHelper.value = isValidUserName
            tilCmPasswordHelper.value = isValidConfirmPassword

            return isValidPassword == null && isValidConfirmPassword==null && isValidPhone == null && isValidFn==null && isValidLn == null && isValidUserName==null
            }



    }






    /** reset login form after successful validation **/
    fun resetLoginForm() {

        email.value = ""
       password.value = ""
        phone.value = ""

        tilEmailHelper.value = null
        tilPasswordHelper.value = null
        tilPhoneHelper.value=  null



    }


    /** reset signUP form after successful validation **/
    fun resetSignUpForm() {

        email.value = ""
        password.value = ""
        phone.value = ""
        username.value = ""
        confirmPassword.value = ""
        firstName.value = ""
        lastName.value = ""


        tilEmailHelper.value = null
        tilPasswordHelper.value = null
        tilPhoneHelper.value=  null
        tilFnHelper.value = null
        tilLnHelper.value = null
        tilUserNameHelper.value = null
        tilCmPasswordHelper.value = null
    }


}


package com.example.demo_app.utils.extensions

import com.example.demo_app.data.local.UserProfile


fun UserProfile.getUserProfile(
    firstName : String,
    lastName : String ,
    age : String ,
  userName : String,
   email : String,
    uid : String) : UserProfile{
      this.firstName = firstName
      this.lastName = lastName
       this.userName = userName
        this.age = age
       this.uid = uid
       this.email = email
    return this
}
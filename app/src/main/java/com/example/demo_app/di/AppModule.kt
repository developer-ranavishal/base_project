package com.example.demo_app.di

import android.content.Context
import android.content.SharedPreferences
import com.example.demo_app.Person
import com.example.demo_app.utils.preferences.PrefManager
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("demo-app", Context.MODE_PRIVATE)
    }



    @Provides
    @Singleton
    fun providePrefManager(sharedPreferences: SharedPreferences): PrefManager {
        return PrefManager(sharedPreferences)
    }


    @Provides
    @Singleton
    fun providePerson() = Person()

}
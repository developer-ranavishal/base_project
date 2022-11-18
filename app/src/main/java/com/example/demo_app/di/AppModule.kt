package com.example.demo_app.di

import android.content.Context
import android.content.SharedPreferences
import com.example.demo_app.Person
import com.example.demo_app.data.remote.ApiService
import com.example.demo_app.data.repo.NewsRepository
import com.example.demo_app.utils.API_KEY
import com.example.demo_app.utils.BASE_URL
import com.example.demo_app.utils.preferences.PrefManager
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import java.util.concurrent.TimeUnit
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
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor {chain ->
                val builder = chain.request().newBuilder()
                builder.header("X-ACCESS-KEY", API_KEY)
                return@addInterceptor chain.proceed(builder.build())
            }
        }.build()

        return okHttpClient
    }



    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL : String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()





    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(apiService: ApiService) = NewsRepository(apiService)




}
package com.example.demo_app.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.demo_app.data.remote.ApiService
import com.example.demo_app.data.remote.data.News
import com.example.demo_app.data.repo.NewsRepository
import com.example.demo_app.utils.preferences.PrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.Closeable
import javax.inject.Inject

@HiltViewModel
class VMHome  @Inject constructor(private val prefManager: PrefManager,private val apiService: ApiService,private val newsRepository: NewsRepository) : ViewModel() {


     val news: LiveData<PagingData<News.Result>> = newsRepository.getNews().cachedIn(viewModelScope)

    fun prefManger() = prefManager

    /** firebase Auth **/
    val firebaseAuth = FirebaseAuth.getInstance()


    /** fireStore database **/
    val db = Firebase.firestore

}



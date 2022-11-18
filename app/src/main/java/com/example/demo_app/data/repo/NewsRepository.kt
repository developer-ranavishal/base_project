package com.example.demo_app.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.demo_app.data.paging.NewsPagingSource
import com.example.demo_app.data.remote.ApiService
import javax.inject.Inject


class NewsRepository @Inject constructor(private val apiService: ApiService){


    fun getNews() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = {NewsPagingSource(apiService = apiService)}
    ).liveData



}
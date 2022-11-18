package com.example.demo_app.data.remote

import com.example.demo_app.data.remote.data.News
import com.example.demo_app.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsdata.io/api/1/news?apikey=pub_135603838f02c374873d9fb7754603cf807bc&page=2
interface ApiService {

    @GET("news?apikey=$API_KEY")
   suspend fun getNews(@Query("page")page : Int) : Response<News>


}







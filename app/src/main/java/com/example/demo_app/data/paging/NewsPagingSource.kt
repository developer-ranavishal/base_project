package com.example.demo_app.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demo_app.data.remote.ApiService
import com.example.demo_app.data.remote.data.News
import javax.inject.Inject

class NewsPagingSource @Inject constructor(private val apiService: ApiService) : PagingSource<Int, News.Result>() {
    override fun getRefreshKey(state: PagingState<Int, News.Result>): Int? {

        return state.anchorPosition?.let {
             state.closestPageToPosition(it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News.Result> {
      return  try {
            val position = params.key?:1
            val response = apiService.getNews(position).body()
            return LoadResult.Page(
                data = response?.results!!,
                prevKey = if (position==1) null else position-1,
                nextKey = if (position==response.totalResults) null else position+1)
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }

}


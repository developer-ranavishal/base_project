package com.example.demo_app.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_app.data.remote.data.News
import com.example.demo_app.databinding.RowNewsLayoutBinding

class NewsPagingAdapter(val onItemClick : (Int,Int,News.Result) -> Unit) : PagingDataAdapter<News.Result,NewsPagingAdapter.NewsViewHolder>(COMPARATOR){



   inner class NewsViewHolder(private val binding : RowNewsLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(news : News.Result,position: Int){
            binding.apply {
                this.news = news
                this.country = news.country[0]
                root.setOnClickListener {
                    onItemClick(0,position,news)
                }

            }

        }


    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        if (news != null) {
            holder.bind(news,position)

        }

    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(RowNewsLayoutBinding.inflate(LayoutInflater.from(parent.context)))



    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<News.Result>(){
            override fun areItemsTheSame(oldItem: News.Result, newItem: News.Result): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: News.Result, newItem: News.Result): Boolean {
                return oldItem==newItem
            }
        }
    }


}
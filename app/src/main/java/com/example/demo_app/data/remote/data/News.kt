package com.example.demo_app.data.remote.data


import com.google.gson.annotations.SerializedName

data class News(
    val nextPage: Int,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
) {
    data class Result(
        val category: List<String>,
        val content: String,
        val country: List<String>,
        val creator: List<String>,
        val description: String,
        @SerializedName("image_url")
        val imageUrl: String,
        val keywords: List<String>,
        val language: String,
        val link: String,
        val pubDate: String,
        @SerializedName("source_id")
        val sourceId: String,
        val title: String,
        @SerializedName("video_url")
        val videoUrl: Any
    )
}
package com.example.knowit.data.remote.model

data class NewsResponse(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)
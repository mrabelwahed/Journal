package com.droidcourses.network.remote.dto


data class NewsResponse(
    val articles: List<ArticleResponse>,
    val status: String,
    val totalResults: Int
)
package com.droidcourses.news_bookmarks.data.remote.dto


data class NewsResponse(
    val articles: List<ArticleResponse>,
    val status: String,
    val totalResults: Int
)
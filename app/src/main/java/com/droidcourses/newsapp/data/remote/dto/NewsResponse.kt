package com.droidcourses.newsapp.data.remote.dto

import com.droidcourses.newsapp.domain.models.Article

data class NewsResponse(
    val articles: List<ArticleResponse>,
    val status: String,
    val totalResults: Int
)
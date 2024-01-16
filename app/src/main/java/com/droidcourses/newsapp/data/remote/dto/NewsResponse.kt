package com.droidcourses.newsapp.data.remote.dto

import com.droidcourses.newsapp.domain.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
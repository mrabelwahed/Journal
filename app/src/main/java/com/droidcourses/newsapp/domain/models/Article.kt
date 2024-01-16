package com.droidcourses.newsapp.domain.models

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)
//TODO: we should create Article DTO and mapper to map from response to domain object
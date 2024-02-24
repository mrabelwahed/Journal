package com.droidcourses.news_bookmarks.data.remote.dto

import com.droidcourses.news_bookmarks.domain.models.Source
import com.google.gson.annotations.SerializedName

class ArticleResponse (
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: Source?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?
)

package com.droidcourses.newsapp.data.mappers

import com.droidcourses.newsapp.data.remote.dto.ArticleResponse
import com.droidcourses.newsapp.domain.models.Article

fun ArticleResponse.toArticle(): Article {
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}
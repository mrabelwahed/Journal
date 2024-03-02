package com.droidcourses.network.mappers

import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.network.remote.dto.ArticleResponse

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
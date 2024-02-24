package com.droidcourses.news_bookmarks.data.mappers

import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.news_bookmarks.data.remote.dto.ArticleResponse

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
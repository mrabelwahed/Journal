package com.droidcourses.database.mapper

import com.droidcourses.database.entity.ArticleEntity
import com.droidcourses.database.entity.SourceEntity
import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.news_bookmarks.domain.models.Source

fun ArticleEntity.toArticle() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source?.toSource(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun SourceEntity.toSource() = Source(
    id = id,
    name = name
)


fun Source.toSource() = SourceEntity(
    id = id,
    name = name
)
fun List<ArticleEntity>.toArticleList(): List<Article> {
  return  this.map {
        it.toArticle()
    }
}

fun Article.toArticleEntity() = ArticleEntity (
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source?.toSource(),
    title = title,
    url = url,
    urlToImage = urlToImage
)
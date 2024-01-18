package com.droidcourses.newsapp.domain.repository.news

import androidx.paging.PagingData
import com.droidcourses.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
     fun getNews(page: Int, query: List<String>): Flow<PagingData<Article>>
     fun searchNews(searchKeyword: String, page: Int, query: List<String>): Flow<PagingData<Article>>
     fun getAllLocalNews(): Flow<List<Article>>
     suspend fun bookmarkArticle(article: Article)
     suspend fun deleteArticle(article: Article)
}
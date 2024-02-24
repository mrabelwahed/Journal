package com.droidcourses.news_bookmarks.domain.usecase

import androidx.paging.PagingData
import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.news_bookmarks.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsUseCase (private val  newsRepository: NewsRepository) {
     fun getNews(query: List<String>): Flow<PagingData<Article>> {
        return  newsRepository.getNews(query = query, page = 1)
    }
    fun searchNews(searchKeyword: String, query: List<String>): Flow<PagingData<Article>> {
        return  newsRepository.searchNews(searchKeyword = searchKeyword, query = query, page = 1)
    }

    fun getAllLocalNews(): Flow<List<Article>> {
        return newsRepository.getAllLocalNews()
    }

    suspend fun bookmarkArticle(article: Article) {
        newsRepository.bookmarkArticle(article)
    }

    suspend fun deleteArticle(article: Article) {
       newsRepository.deleteArticle(article)
    }
}
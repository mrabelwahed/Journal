package com.droidcourses.newsapp.domain.usecase

import androidx.paging.PagingData
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.domain.repository.news.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase (private val  newsRepository: NewsRepository) {
     fun getNews(query: List<String>): Flow<PagingData<Article>> {
        return  newsRepository.getNews(query = query, page = 1)
    }
    fun searchNews(searchKeyword: String, query: List<String>): Flow<PagingData<Article>> {
        return  newsRepository.searchNews(searchKeyword = searchKeyword, query = query, page = 1)
    }
}
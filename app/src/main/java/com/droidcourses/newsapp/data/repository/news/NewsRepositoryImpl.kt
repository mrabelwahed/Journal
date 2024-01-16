package com.droidcourses.newsapp.data.repository.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.droidcourses.newsapp.data.paging.NewsPagingSource
import com.droidcourses.newsapp.data.remote.api.NewsAPI
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.domain.repository.news.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsAPI: NewsAPI) : NewsRepository {
    override  fun getNews(page: Int, query: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(query, newsAPI)
            }
        ).flow
    }

    override  fun searchNews(searchKeyword: String, page: Int, query: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(query, newsAPI)
            }
        ).flow
    }

}
package com.droidcourses.news_bookmarks.data.repository.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.news_bookmarks.domain.repository.NewsRepository
import com.droidcourses.news_bookmarks.data.paging.NewsPagingSource
import com.droidcourses.news_bookmarks.data.paging.NewsSearchPagingSource
import com.droidcourses.news_bookmarks.data.remote.api.NewsAPI
import com.droidcourses.news_bookmarks.data.local.NewsDao
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsAPI: NewsAPI, private val newsDao: NewsDao
) :
    NewsRepository {
    override  fun getNews(page: Int, query: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(query, newsAPI)
            }
        ).flow
    }

    override fun searchNews(searchKeyword: String, page: Int, query: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsSearchPagingSource(searchKeyword, query, newsAPI)
            }
        ).flow
    }

    override fun getAllLocalNews(): Flow<List<Article>> {
       return  newsDao.getAllArticles()
    }

    override suspend fun bookmarkArticle(article: Article) {
         newsDao.addArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
         newsDao.deleteArticle(article)
    }
}
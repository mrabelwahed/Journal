package com.droidcourses.data.repository.news


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.droidcourses.database.local.NewsDao
import com.droidcourses.database.mapper.toArticleEntity
import com.droidcourses.database.mapper.toArticleList
import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.news_bookmarks.domain.repository.NewsRepository
import com.droidcourses.network.paging.NewsPagingSource
import com.droidcourses.network.paging.NewsSearchPagingSource
import com.droidcourses.network.remote.api.NewsAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(private val newsAPI: NewsAPI, private val newsDao: NewsDao) :
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
       return  newsDao.getAllArticles().map {
           it.toArticleList()
       }
    }

    override suspend fun bookmarkArticle(article: Article) {
         newsDao.addArticle(article.toArticleEntity())
    }

    override suspend fun deleteArticle(article: Article) {
         newsDao.deleteArticle(article.toArticleEntity())
    }
}
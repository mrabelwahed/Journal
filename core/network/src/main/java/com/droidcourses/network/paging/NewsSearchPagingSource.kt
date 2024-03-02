package com.droidcourses.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.network.mappers.toArticle
import com.droidcourses.network.remote.api.NewsAPI


class NewsSearchPagingSource(
    private val searchKeyword: String,
    private val query: List<String>,
    private val newsAPI: NewsAPI
) : PagingSource<Int, Article>() {

    private var newsCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        println("page:$page")
        val response = newsAPI.searchNews(searchKeyword = searchKeyword, query = query.joinToString(","), page)
        newsCount += response.articles.count()
        val prevKey = null
        val nextKey = if (response.totalResults == newsCount) null else page + 1

        return try {
            LoadResult.Page(
                data = response.articles.distinctBy { it.title }.map { it.toArticle() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            println(e.printStackTrace())
            LoadResult.Error(Throwable(e))
        }
    }
}
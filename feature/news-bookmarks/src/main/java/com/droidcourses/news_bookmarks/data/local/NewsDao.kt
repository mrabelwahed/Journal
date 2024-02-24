package com.droidcourses.news_bookmarks.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droidcourses.news_bookmarks.domain.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM Article")
    fun getAllArticles(): Flow<List<Article>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: Article)
    @Delete
    suspend fun deleteArticle(article: Article)
}
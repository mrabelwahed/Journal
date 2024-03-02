package com.droidcourses.database.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droidcourses.database.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM ArticleEntity")
    fun getAllArticles(): Flow<List<ArticleEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: ArticleEntity)
    @Delete
    suspend fun deleteArticle(article: ArticleEntity)
}
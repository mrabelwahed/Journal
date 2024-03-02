package com.droidcourses.news_bookmarks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.droidcourses.database.entity.ArticleEntity
import com.droidcourses.database.local.NewsDao
import com.droidcourses.database.local.NewsTypeConverter

@Database(entities = [ArticleEntity::class], version = 2)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao (): NewsDao
}
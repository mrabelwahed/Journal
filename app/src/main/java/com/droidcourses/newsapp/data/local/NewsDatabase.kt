package com.droidcourses.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.droidcourses.newsapp.domain.models.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao (): NewsDao
}
package com.droidcourses.database.di

import android.app.Application
import androidx.room.Room
import com.droidcourses.common.util.AppConst
import com.droidcourses.database.local.NewsDao
import com.droidcourses.news_bookmarks.data.local.NewsDatabase
import com.droidcourses.database.local.NewsTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(application,NewsDatabase::class.java, AppConst.DATABASE_NAME)
            .addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsDao (newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsDao()
    }
}
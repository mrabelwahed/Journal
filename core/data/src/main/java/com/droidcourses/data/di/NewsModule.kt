package com.droidcourses.news_bookmarks.di

import com.droidcourses.news_bookmarks.domain.repository.NewsRepository
import com.droidcourses.news_bookmarks.domain.usecase.NewsUseCase
import com.droidcourses.network.remote.api.NewsAPI
import com.droidcourses.data.repository.news.NewsRepositoryImpl
import com.droidcourses.common.util.AppConst
import com.droidcourses.database.local.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun providesNewsAPI(): NewsAPI = Retrofit.Builder()
        .baseUrl(AppConst.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsAPI::class.java)


    @Provides
    @Singleton
    fun providesNewsRepository(newsAPI: NewsAPI, newsDao: NewsDao): NewsRepository = NewsRepositoryImpl(newsAPI, newsDao)
    @Provides
    @Singleton
    fun providesNewsUseCase(newsRepository: NewsRepository) = NewsUseCase(newsRepository)

}
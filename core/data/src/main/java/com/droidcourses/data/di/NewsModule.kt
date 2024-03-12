package com.droidcourses.news_bookmarks.di

import com.droidcourses.common.util.AppConst
import com.droidcourses.data.repository.news.NewsRepositoryImpl
import com.droidcourses.database.local.NewsDao
import com.droidcourses.network.remote.api.NewsAPI
import com.droidcourses.news_bookmarks.domain.repository.NewsRepository
import com.droidcourses.news_bookmarks.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
 object NewsModule {

     @Provides
     @Singleton
     fun providesOkhttp() : OkHttpClient {
         val logging = HttpLoggingInterceptor()
         logging.setLevel(HttpLoggingInterceptor.Level.BODY)
         return  OkHttpClient.Builder()
             .addInterceptor(logging)
             .build()
     }

    @Provides
    @Singleton
    fun providesNewsAPI(client: OkHttpClient): NewsAPI = Retrofit.Builder()
        .baseUrl(AppConst.BASE_URL)
        .client(client)
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
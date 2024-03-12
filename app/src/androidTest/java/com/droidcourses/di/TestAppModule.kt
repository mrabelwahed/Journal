package com.droidcourses.di

import android.app.Application
import androidx.room.Room
import com.droidcourses.data.repository.news.NewsRepositoryImpl
import com.droidcourses.database.di.DatabaseModule
import com.droidcourses.database.local.NewsDao
import com.droidcourses.network.remote.api.NewsAPI
import com.droidcourses.database.local.NewsDatabase
import com.droidcourses.database.local.NewsTypeConverter
import com.droidcourses.news_bookmarks.di.NewsModule
import com.droidcourses.news_bookmarks.domain.repository.NewsRepository
import com.droidcourses.news_bookmarks.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NewsModule::class, DatabaseModule::class]
    )
object TestAppModule {
    @Provides
    @Singleton
    fun providesDatabase(application: Application): NewsDatabase {
        return Room.inMemoryDatabaseBuilder(
            application,
            NewsDatabase::class.java,
        )
            .addTypeConverter(NewsTypeConverter())
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsDao (newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsDao()
    }

    @Provides
    @Singleton
    fun providesOkhttp() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return  OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsAPI(client: OkHttpClient): NewsAPI = Retrofit.Builder()
        .baseUrl("http://localhost:8080")
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
package com.droidcourses.newsapp.di


import android.app.Application
import com.droidcourses.newsapp.data.remote.api.NewsAPI
import com.droidcourses.newsapp.data.repository.news.NewsRepositoryImpl
import com.droidcourses.newsapp.data.repository.user.LocalUserManagerImpl
import com.droidcourses.newsapp.domain.repository.news.NewsRepository
import com.droidcourses.newsapp.domain.repository.user.LocalUserManager
import com.droidcourses.newsapp.domain.usecase.GetNewsUseCase
import com.droidcourses.newsapp.domain.usecase.OnBoardingUseCase
import com.droidcourses.newsapp.util.AppConst
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesUserLocalManager(application: Application): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun providesOBoardingUseCase(localUserManager: LocalUserManager) = OnBoardingUseCase(localUserManager)

    @Provides
    @Singleton
    fun providesNewsAPI(): NewsAPI = Retrofit.Builder()
        .baseUrl(AppConst.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsAPI::class.java)
    @Provides
    @Singleton
    fun providesNewsRepository(newsAPI: NewsAPI): NewsRepository = NewsRepositoryImpl(newsAPI)

    @Provides
    @Singleton
    fun providesNewsUseCase(newsRepository: NewsRepository) = GetNewsUseCase(newsRepository)
}
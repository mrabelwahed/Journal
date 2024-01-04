package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.LocalUserManagerImpl
import com.loc.newsapp.domain.LocalUserManager
import com.loc.newsapp.domain.OnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}
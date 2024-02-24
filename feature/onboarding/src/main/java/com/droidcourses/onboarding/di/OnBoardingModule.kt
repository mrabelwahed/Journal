package com.droidcourses.onboarding.di

import android.app.Application
import com.droidcourses.onboarding.data.LocalUserManagerImpl
import com.droidcourses.onboarding.domain.LocalUserManager
import com.droidcourses.onboarding.domain.usecase.OnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnBoardingModule {

    @Provides
    @Singleton
    fun providesUserLocalManager(application: Application): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun providesOBoardingUseCase(localUserManager: LocalUserManager) = OnBoardingUseCase(localUserManager)
}
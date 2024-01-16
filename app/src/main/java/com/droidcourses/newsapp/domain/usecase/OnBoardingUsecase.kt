package com.droidcourses.newsapp.domain.usecase

import com.droidcourses.newsapp.domain.repository.user.LocalUserManager


class OnBoardingUseCase(private val localUserManager: LocalUserManager) {

    fun isOnBoardingVisited()  = localUserManager.isOnBoardingVisited()

    suspend fun setOnBoardingVisited(isVisited: Boolean) {
        localUserManager.setOnBoardingVisited(isVisited)
    }
}
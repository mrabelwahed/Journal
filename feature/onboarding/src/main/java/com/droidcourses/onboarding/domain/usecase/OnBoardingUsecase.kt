package com.droidcourses.onboarding.domain.usecase

import com.droidcourses.onboarding.domain.LocalUserManager


class OnBoardingUseCase(private val localUserManager: LocalUserManager) {

    fun isOnBoardingVisited()  = localUserManager.isOnBoardingVisited()

    suspend fun setOnBoardingVisited(isVisited: Boolean) {
        localUserManager.setOnBoardingVisited(isVisited)
    }
}
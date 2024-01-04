package com.loc.newsapp.domain


class OnBoardingUseCase(private val localUserManager: LocalUserManager) {

    fun isOnBoardingVisited()  = localUserManager.isOnBoardingVisited()

    suspend fun setOnBoardingVisited(isVisited: Boolean) {
        localUserManager.setOnBoardingVisited(isVisited)
    }
}
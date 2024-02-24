package com.droidcourses.onboarding.domain

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun setOnBoardingVisited(isVisited: Boolean)
    fun isOnBoardingVisited() : Flow<Boolean>
}

//TODO: Move this to local module
package com.droidcourses.newsapp.domain.repository.user

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun setOnBoardingVisited(isVisited: Boolean)
    fun isOnBoardingVisited() : Flow<Boolean>
}
package com.loc.newsapp.domain

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun setOnBoardingVisited(isVisited: Boolean)
    fun isOnBoardingVisited() : Flow<Boolean>
}
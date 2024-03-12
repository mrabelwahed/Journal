package com.droidcourses.news.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.droidcourses.news_bookmarks.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(newsUseCase: NewsUseCase): ViewModel() {

    var  state = mutableStateOf(HomeState())
        private set

    private val query = listOf("bbc-news")
    val news = newsUseCase.getNews(query).cachedIn(viewModelScope)

}

data class HomeState(var isLoading: Boolean =  false)

sealed class HomeScreenEvent () {
    object OpenSearch : HomeScreenEvent()
}
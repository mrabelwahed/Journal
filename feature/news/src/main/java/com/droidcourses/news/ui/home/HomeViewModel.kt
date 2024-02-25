package com.droidcourses.news.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.droidcourses.news_bookmarks.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCase: com.droidcourses.news_bookmarks.domain.usecase.NewsUseCase): ViewModel() {

    var  state = mutableStateOf(HomeState())
        private set

    private val query = listOf("bbc-news","abc-news","al-jazeera-english")
    val news = newsUseCase.getNews(query).cachedIn(viewModelScope)

//    private fun onEvent(event: HomeScreenEvent) {
////        when(event) {
//////            is HomeScreenEvent.OpenSearch ->
////        }
//    }

}

data class HomeState(var isLoading: Boolean =  false)

sealed class HomeScreenEvent () {
    object OpenSearch : HomeScreenEvent()
}
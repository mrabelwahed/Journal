package com.droidcourses.newsapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val newsUseCase: NewsUseCase): ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchScreenEvent) {
        when(event) {
            is SearchScreenEvent.SearchEvent -> {
                searchNews()
            }
            is SearchScreenEvent.UpdateSearchKeyword ->{
                _state.value = SearchState(event.updatedKeyword)
            }

        }
    }

    private fun searchNews() {
        val list = listOf("bbc-news","abc-news","al-jazeera-english")
        val articles =  newsUseCase.searchNews(searchKeyword = _state.value.searchQuery, query = list)
            .cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles = articles)
    }

}


data class SearchState (
    var searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)

sealed class SearchScreenEvent() {
    object SearchEvent : SearchScreenEvent()
    data class UpdateSearchKeyword(val updatedKeyword: String): SearchScreenEvent()
}



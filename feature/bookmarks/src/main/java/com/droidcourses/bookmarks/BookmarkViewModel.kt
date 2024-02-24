package com.droidcourses.bookmarks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.news_bookmarks.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val newsUseCase: NewsUseCase): ViewModel() {

    private var _localNewsState = mutableStateOf(BookmarkState())
    val localNewsState: State<BookmarkState> = _localNewsState
    init {
        getAllLocalNews()
    }
    private fun getAllLocalNews() {
        viewModelScope.launch {
            newsUseCase.getAllLocalNews()
                .collect{
                    _localNewsState.value = BookmarkState(it)
                }
        }
    }

     fun onEvent(event: BookmarkScreenEvent) {
        when(event) {
            is BookmarkScreenEvent.DeleteArticleEvent -> deleteArticle(event.article)
        }
    }

    private fun  deleteArticle(article: Article) {
        viewModelScope.launch {
            newsUseCase.deleteArticle(article)
        }
    }
}

data class BookmarkState(val data: List<Article> =  emptyList())

sealed class BookmarkScreenEvent {
    data class DeleteArticleEvent(val article: Article): BookmarkScreenEvent()
}
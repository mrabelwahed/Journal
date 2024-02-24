package com.droidcourses.news.ui.details

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcourses.news_bookmarks.domain.models.Article
import com.droidcourses.news_bookmarks.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (private val newsUseCase: NewsUseCase) :
    ViewModel() {


    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.BookmarkClicked -> onBookmarkClicked(event.article)
        }
    }


    private fun onBookmarkClicked(article: Article) {
        viewModelScope.launch {
            newsUseCase.bookmarkArticle(article)
        }.invokeOnCompletion {
            //TODO: refactor this
            //Toast.makeText(context, "Bookmarked Successfully", Toast.LENGTH_SHORT).show()
        }
    }

}

sealed class DetailsScreenEvent {
    data class BookmarkClicked(val article: com.droidcourses.news_bookmarks.domain.models.Article) : DetailsScreenEvent()
}
package com.droidcourses.newsapp.presentation.details

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.droidcourses.newsapp.app.NewsApp
import com.droidcourses.newsapp.domain.models.Article
import com.droidcourses.newsapp.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (application: Application, private val newsUseCase: NewsUseCase) :
    AndroidViewModel(application) {

    private val context = getApplication<NewsApp>()
    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.ShareClicked -> onShareClicked(event.url)
            is DetailsScreenEvent.BrowseClicked -> onBrowserClicked(event.url)
            is DetailsScreenEvent.BookmarkClicked -> onBookmarkClicked(event.article)
        }
    }

    private fun onShareClicked(url: String) {
        Intent().also {
            it.action = Intent.ACTION_SEND
            it.putExtra(Intent.EXTRA_TEXT, url)
            it.type = "text/plain"
            it.addFlags(FLAG_ACTIVITY_NEW_TASK)
            if (it.resolveActivity(context.packageManager) != null)
                context.startActivity(it)
        }
    }

    private fun onBrowserClicked(url: String) {
        Intent().also {
            it.action = Intent.ACTION_VIEW
            it.data = Uri.parse(url)
            it.addFlags(FLAG_ACTIVITY_NEW_TASK)
            if (it.resolveActivity(context.packageManager) != null)
                context.startActivity(it)
        }
    }


    private fun onBookmarkClicked(article:Article) {
        viewModelScope.launch {
            newsUseCase.bookmarkArticle(article)
        }.invokeOnCompletion {
            Toast.makeText(context, "Bookmarked Successfully", Toast.LENGTH_SHORT).show()
        }
    }

}

sealed class DetailsScreenEvent {
    data class ShareClicked(val url: String) : DetailsScreenEvent()
    data class BookmarkClicked(val article: Article) : DetailsScreenEvent()
    data class BrowseClicked(val url: String) : DetailsScreenEvent()
}
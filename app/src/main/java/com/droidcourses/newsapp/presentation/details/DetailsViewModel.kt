package com.droidcourses.newsapp.presentation.details

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.droidcourses.newsapp.app.NewsApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor (application: Application) :
    AndroidViewModel(application) {

    private val context = getApplication<NewsApp>()
    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.ShareClicked -> onShareClicked(event.url)
            is DetailsScreenEvent.BrowseClicked -> onBrowserClicked(event.url)
            is DetailsScreenEvent.BookmarkClicked -> onBookmarkClicked()
            is DetailsScreenEvent.NavigateUp -> onNavigateUp()
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


    private fun onBookmarkClicked() {}

    private fun onNavigateUp() {}
}

sealed class DetailsScreenEvent() {
    object NavigateUp : DetailsScreenEvent()
    data class ShareClicked(val url: String) : DetailsScreenEvent()
    object BookmarkClicked : DetailsScreenEvent()
    data class BrowseClicked(val url: String) : DetailsScreenEvent()
}
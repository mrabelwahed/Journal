package com.droidcourses.search

import androidx.activity.compose.setContent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.droidcourses.BaseTest
import com.droidcourses.SuccessDispatcher
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NewsSearchTest : BaseTest(){

    @Test
    fun shouldShowEmptyWhenLaunchSearch() {
      mockWebServer.dispatcher = SuccessDispatcher()
        composeRule.activity.setContent {
            SearchScreen {}
        }
        launchNewsSearchScreen(composeRule) {

        } verify {
            emptySearchViewShouldBeDisplayed()
        }
    }
}
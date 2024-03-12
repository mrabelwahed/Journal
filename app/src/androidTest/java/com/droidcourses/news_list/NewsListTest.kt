package com.droidcourses.news_list

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.droidcourses.BaseTest
import com.droidcourses.SuccessDispatcher
import com.droidcourses.news.ui.home.HOME_ROUTE
import com.droidcourses.news.ui.home.HomeScreen
import com.droidcourses.newsapp.presentation.activity.MainActivity
import com.droidcourses.newsapp.presentation.activity.MainScreen
import com.droidcourses.newsapp.presentation.activity.MainViewModel
import com.droidcourses.onboarding.launchOnBoardingScreen
import com.droidcourses.onboarding.ui.nav.ONBOARDING_ROUTE
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runBlockingTestOnTestScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NewsListTest : BaseTest(){

    @Test
    fun articleListShouldBeDisplayedWhenLaunchScreen() {

        mockWebServer.dispatcher = SuccessDispatcher()
        composeRule.activity.setContent {
            HomeScreen {}
        }

      launchNewsListScreen (composeRule) {
          waitUntilArticleListLoaded()
      }

    }

}
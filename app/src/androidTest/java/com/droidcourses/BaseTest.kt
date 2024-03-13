package com.droidcourses

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import com.droidcourses.designsystem.NewsAppTheme
import com.droidcourses.newsapp.presentation.activity.MainActivity
import com.droidcourses.newsapp.presentation.activity.MainScreen
import com.droidcourses.newsapp.presentation.activity.MainViewModel
import com.droidcourses.onboarding.ui.nav.ONBOARDING_ROUTE
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule


open class BaseTest {
    @get:Rule(order = 0)
    val hiltRule by lazy { HiltAndroidRule(this) }
    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

     val mockWebServer = MockWebServer()
    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer.start(8080)
        viewModel = composeRule.activity.viewModels<MainViewModel>().value
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
package com.droidcourses.onboarding

import androidx.activity.viewModels
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.droidcourses.BaseTest
import com.droidcourses.SuccessDispatcher
import com.droidcourses.news.ui.home.HOME_ROUTE
import com.droidcourses.newsapp.presentation.activity.MainActivity
import com.droidcourses.newsapp.presentation.activity.MainViewModel
import com.droidcourses.onboarding.ui.nav.ONBOARDING_ROUTE
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class OnBoardingTest: BaseTest() {

//    @get:Rule(order = 0)
//    val hiltRule = HiltAndroidRule(this)
//
//    @get:Rule(order = 1)
//    val composeRule = createAndroidComposeRule<MainActivity>()
//
//    private lateinit var viewModel:MainViewModel

//    @Before
//    fun setup() {
//        viewModel = composeRule.activity.viewModels<MainViewModel>().value
//    }
    @Test
    fun backButtonShouldBeDisplayed_WhenClickNextButton() {
        viewModel._startDestination.value = ONBOARDING_ROUTE
        launchOnBoardingScreen(
            rule = composeRule
        ) {
            clickNext()
        } verify {
            nextPageIsVisible()
        }
    }

    @Test
    fun getStartedButtonShouldBeDisplayed_WhenClickNextButtonTwice() {
        viewModel._startDestination.value = ONBOARDING_ROUTE
        launchOnBoardingScreen(
            rule = composeRule
        ) {
            clickNext()
            clickNext()
        } verify {
            getStartedButtonIsVisible()
        }
    }

    @Test
    fun backButtonShouldNotBeExistedInTheFirstPage() {
        viewModel._startDestination.value = ONBOARDING_ROUTE
        launchOnBoardingScreen(
            rule = composeRule
        ) {
            clickNext()
            clickBack()
        } verify {
            backButtonIsNotExisted()
        }
    }

    @Test
    fun shouldShowHomeScreenAfterSuccessfulOnboarding() {
        viewModel._startDestination.value = ONBOARDING_ROUTE
        mockWebServer.dispatcher = SuccessDispatcher()
        launchOnBoardingScreen(
            rule = composeRule
        ) {
            clickNext()
            clickNext()
            clickGetStartedButton()
            viewModel._startDestination.value = HOME_ROUTE
            waitUntilNextScreenIsVisible()
        } verify {
            newsListScreenIsVisible()
        }
    }

}




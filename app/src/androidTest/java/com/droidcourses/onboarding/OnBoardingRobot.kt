package com.droidcourses.onboarding

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.droidcourses.newsapp.presentation.activity.MainActivity
import kotlinx.coroutines.delay

class OnBoardingRobot(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>){
    infix fun verify(block: OnBoardingVerification.() -> Unit): OnBoardingVerification {
        return OnBoardingVerification(rule).apply (block)
    }

    fun clickNext() {
        rule.onNodeWithText("Next")
            .performClick()
    }

    fun clickBack() {
        rule.onNodeWithText("Back")
            .performClick()
    }

    fun clickGetStartedButton() {
        rule.onNodeWithText("Get Started")
            .performClick()
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitUntilNextScreenIsVisible() {
        rule.waitUntilExactlyOneExists(hasText("Journal"))
    }

}

 fun launchOnBoardingScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>,MainActivity>,
    block: OnBoardingRobot.() -> Unit
): OnBoardingRobot {
    return OnBoardingRobot(rule).apply(block)
}




class OnBoardingVerification(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>,MainActivity>) {
    fun nextPageIsVisible() {
       rule.onNodeWithText("Back")
           .assertIsDisplayed()
    }

    fun getStartedButtonIsVisible() {
        rule.onNodeWithText("Get Started")
            .assertIsDisplayed()
    }

    fun backButtonIsNotExisted() {
        rule.onNodeWithText("Back")
            .assertDoesNotExist()
    }

     @OptIn(ExperimentalTestApi::class)
     fun newsListScreenIsVisible() {
         rule.waitUntilAtLeastOneExists(hasContentDescription("Article List"))
    }
}
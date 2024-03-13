package com.droidcourses.search

import androidx.annotation.DrawableRes
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.droidcourses.newsapp.R
import com.droidcourses.newsapp.presentation.activity.MainActivity
import com.droidcourses.onboarding.OnBoardingVerification

class NewsSearchRobot(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>){

    infix fun verify(block: NewsSearchVerification.() -> Unit): NewsSearchVerification {
        return NewsSearchVerification(rule).apply (block)
    }

    fun enterSearchKeyword() {
        rule.onNodeWithContentDescription("Search Bar")
            .performTextInput("sport")
    }

    fun performSearchAction() {
        rule.onNodeWithContentDescription("Search Bar")
            .performImeAction()
    }

    fun advanceClockUntilIdle() {
        rule.mainClock.autoAdvance = true
        rule.waitForIdle()
    }



}


class NewsSearchVerification(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>,MainActivity>) {

    fun emptySearchViewShouldBeDisplayed() {
        rule.onNodeWithText("Search by keyword")
            .assertIsDisplayed()
        rule.onNode(hasDrawable(com.droidcourses.designsystem.R.drawable.ic_search_document))
            .assertIsDisplayed()
    }

    private fun hasDrawable(@DrawableRes id: Int): SemanticsMatcher {
       return SemanticsMatcher.expectValue(drawableID, id)
    }

    @OptIn(ExperimentalTestApi::class)
    fun waitUntilArticleListLoaded() {
        rule.waitUntilAtLeastOneExists(hasContentDescription("Article List"))
    }
}
fun launchNewsSearchScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: NewsSearchRobot.() -> Unit
): NewsSearchRobot {
    return NewsSearchRobot(rule).apply(block)
}
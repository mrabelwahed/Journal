package com.droidcourses.news_list

import androidx.compose.ui.test.ExperimentalTestApi
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
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.droidcourses.newsapp.presentation.activity.MainActivity

class NewsListRobot(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>){
    @OptIn(ExperimentalTestApi::class)
    fun waitUntilArticleListLoaded() {
        rule.waitUntilAtLeastOneExists(hasContentDescription("Article List"))
    }

    fun advanceClockUntilIdle() {
        rule.mainClock.autoAdvance = true
        rule.waitForIdle()
    }

    fun clickOnTheFirstArticleInTheList() {
        rule.onNodeWithContentDescription("Article List" , useUnmergedTree = false)
            .onChildren()
            .onFirst()
            .performClick()
    }


    fun firstArticleListTitleIsDisplayed() {
        rule.onNodeWithText("Ministers call for defence spending of 2.5% of GDP")
            .assertIsDisplayed()
    }


}

fun launchNewsListScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: NewsListRobot.() -> Unit
): NewsListRobot {
    return NewsListRobot(rule).apply(block)
}
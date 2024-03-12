package com.droidcourses.news_list

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.droidcourses.newsapp.presentation.activity.MainActivity

class NewsListRobot(private val rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>){
    fun waitUntilArticleListLoaded() {
        rule.waitUntil(2000) {
            rule.onAllNodesWithContentDescription(
                "Article",
                substring = true,
                useUnmergedTree = false
            ).fetchSemanticsNodes().size == 3
        }
    }
}

fun launchNewsListScreen(
    rule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    block: NewsListRobot.() -> Unit
): NewsListRobot {
    return NewsListRobot(rule).apply(block)
}
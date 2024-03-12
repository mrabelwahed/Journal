package com.droidcourses.suite

import com.droidcourses.news_list.NewsListTest
import com.droidcourses.onboarding.OnBoardingTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(OnBoardingTest::class, NewsListTest::class)
class EndToEndTesting
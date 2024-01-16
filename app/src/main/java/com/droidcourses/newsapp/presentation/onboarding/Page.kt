package com.droidcourses.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.droidcourses.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


val pages = mutableListOf(
    Page(
        title = "What is Lorem Ipsum?",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.",
        image = R.drawable.onboardinx
    ),

    Page(
        title = "What is Lorem Ipsum?",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.",
        image = R.drawable.onboarding2x
    ),

    Page(
        title = "What is Lorem Ipsum?",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley" +
                " of type and scrambled it to make a type specimen book.",
        image = R.drawable.onboarding3x,
    )
)
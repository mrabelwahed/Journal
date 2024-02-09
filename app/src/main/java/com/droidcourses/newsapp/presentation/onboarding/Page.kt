package com.droidcourses.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.droidcourses.designsystem.R as designR

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


val pages = mutableListOf(
    Page(
        title = "Welcome to Journal?",
        description = "Dive into a world of endless possibilities with Journal. Discover intuitive features, personalized content, and seamless navigation that make your experience truly one-of-a-kind. Get ready to embark on a journey where convenience meets innovation!",
        image = designR.drawable.onboardinx
    ),

    Page(
        title = "Explore Your Interests",
        description = "Description: Tailor your Journal experience by selecting your interests. Whether it's the latest trends, breaking news, or niche hobbies, we've got you covered. Unleash a personalized feed curated just for you and stay engaged with the content that matters most.",
        image = designR.drawable.onboarding2x
    ),

    Page(
        title = "Connect and Share with Newsy Community",
        description = "Description: Join a vibrant community of like-minded individuals on Journal. Share your thoughts, discover new perspectives, and connect with people who share your passions. Engage in discussions, make new friends, and build a network that enhances your journey within the [Your App Name] community.",
        image = designR.drawable.onboarding3x,
    )
)
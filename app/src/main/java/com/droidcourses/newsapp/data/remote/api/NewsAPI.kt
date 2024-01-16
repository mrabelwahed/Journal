package com.droidcourses.newsapp.data.remote.api

import com.droidcourses.newsapp.data.remote.dto.NewsResponse
import com.droidcourses.newsapp.util.AppConst.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    //TODO:  passing key like this is not secure, we need to secure this later
    @GET("everything")
    suspend fun getNews(
        @Query("sources") query: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchKeyword:String,
        @Query("sources") query: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}
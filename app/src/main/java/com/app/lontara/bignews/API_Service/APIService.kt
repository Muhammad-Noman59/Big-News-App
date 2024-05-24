package com.app.lontara.bignews.API_Service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService{

    @GET("top-headlines")

    suspend fun getAllNews(
        @Query("country") country : String = "us",
        @Query("sortBy") sortBy : String = "publishedAt",
        @Query("category") category : String?,
        @Query("apiKey") apiKey : String = "Your API Key"
    ) : Response<NewsModel>

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "Your API Key"
    ): Response<NewsModel>



}


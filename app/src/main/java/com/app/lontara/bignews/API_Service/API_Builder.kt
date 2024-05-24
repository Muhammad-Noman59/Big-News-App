package com.app.lontara.bignews.API_Service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object API_Builder {


    val client = OkHttpClient.Builder()
        .connectTimeout(5,TimeUnit.MINUTES)
        .build()

    fun provideAPI() =
        Retrofit.Builder().client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService::class.java)
}
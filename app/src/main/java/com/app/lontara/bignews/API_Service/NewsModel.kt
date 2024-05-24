package com.app.lontara.bignews.API_Service

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
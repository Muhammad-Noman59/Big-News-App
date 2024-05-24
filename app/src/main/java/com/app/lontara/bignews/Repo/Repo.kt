package com.app.lontara.bignews.Repo

import com.app.lontara.bignews.API_Service.API_Builder
import com.app.lontara.bignews.API_Service.NewsModel
import retrofit2.Response
import retrofit2.http.Query

class Repo {

    suspend fun newsProvider(category: String?, query: String?) : Response<NewsModel>{

        return if (query.isNullOrEmpty()){
            API_Builder.provideAPI().getAllNews(category = category)
        }else{
            API_Builder.provideAPI().searchNews(query = query)
        }






    }
}
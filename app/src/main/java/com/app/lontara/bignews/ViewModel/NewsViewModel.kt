package com.app.lontara.bignews.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lontara.bignews.API_Service.NewsModel
import com.app.lontara.bignews.Repo.Repo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel (): ViewModel() {

    val res = mutableStateOf<NewsModel?>(null)
    val category = mutableStateOf<String?>(null)

    val query = mutableStateOf<String?>(null)

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    init {
        fetchNews()
    }

    fun fetchNews(){

        viewModelScope.launch {
            res.value = getNews(Repo(),category = category.value, newQuery = query.value)

        }
    }

   suspend fun getNews(repo: Repo, category: String?,newQuery: String?) : NewsModel?{

        return repo.newsProvider(category = category, query = newQuery).body()
    }

    fun refreshNews() {
        viewModelScope.launch {
            _isRefreshing.value = true
            // Fetch news data here
            res.value = getNews(Repo(), category = category.value, newQuery = query.value)
            _isRefreshing.value = false
        }
    }


    fun updateCategory(newCategory: String?){

        category.value = newCategory
        fetchNews()

    }

    fun updateQuery(newQuery : String?){
        query.value = newQuery
        fetchNews()
    }

}
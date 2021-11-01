package com.android.example.newapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _property = MutableLiveData<List<Article>>()
    val property: LiveData<List<Article>>
        get() = _property

    private val _ukNewsList = MutableLiveData<List<Article>>()
    val ukNewsList: LiveData<List<Article>>
        get() = _ukNewsList

    private val _navigateNewsDetails = MutableLiveData<Article?>()
    val navigateNewsDetail: LiveData<Article?>
         get() = _navigateNewsDetails


    init {
       getNews()
    }


    private fun getNews(){
        viewModelScope.launch {

            _networkState.value = NetworkState.LOADING

            try {
                val resultObj = NewsObj.newsService.getNewsProperties()
                _property.value = resultObj.articles
                _networkState.value = NetworkState.SUCCESS

            }catch (e: Exception){
                _networkState.value = NetworkState.ERROR
                _property.value = ArrayList()
            }
        }
    }

     fun getUkNews(){
        viewModelScope.launch {
            try {
                val resultObj = NewsObj.newsService.getUkNewsProperties()
                _response.value = "success: $resultObj"

                _property.value = resultObj.articles

            }catch (e: Exception){
                _response.value = "Failure ${e.message}"
            }
        }
    }

    fun getSportsNews(){
        viewModelScope.launch {
            try {
                val resultObj = NewsObj.newsService.getSportsNews()

                _property.value = resultObj.articles

            }catch (e: Exception){
                _response.value = "Failure ${e.message}"
            }
        }
    }

    fun getNgNews(){
        viewModelScope.launch {
            try {
                val resultObj = NewsObj.newsService.getNgNews()
                _response.value = "success: $resultObj"

                _property.value = resultObj.articles

            }catch (e: Exception){
                _response.value = "Failure ${e.message}"
            }
        }
    }


    fun displayNewsDetail(article: Article){
        _navigateNewsDetails.value = article
    }

    fun doneNavigatingNewsDetail(){
        _navigateNewsDetails.value = null
    }

}
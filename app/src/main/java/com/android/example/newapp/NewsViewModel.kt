package com.android.example.newapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.newapp.Article
import com.android.example.newapp.Models.NetworkState
import com.android.example.newapp.NewsObj
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _property = MutableLiveData<List<Article>>()
    val property: LiveData<List<Article>>
        get() = _property

    private val _navigateNewsDetails = MutableLiveData<Article?>()
    val navigateNewsDetail: LiveData<Article?>
         get() = _navigateNewsDetails

    /**
    getNews() function called when Viewmodel is initialized.
     */
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

            }
        }
    }

     fun getUkNews(){
        viewModelScope.launch {
            try {
                val resultObj = NewsObj.newsService.getUkNewsProperties()
                _property.value = resultObj.articles

            }catch (e: Exception){
                _property.value = ArrayList()
            }
        }
    }

    fun getSportsNews(){
        viewModelScope.launch {
            try {
                val resultObj = NewsObj.newsService.getSportsNews()
                _property.value = resultObj.articles

            }catch (e: Exception){
                _property.value = ArrayList()
            }
        }
    }

    fun getNgNews(){
        viewModelScope.launch {
            try {
                val resultObj = NewsObj.newsService.getNgNews()
                _property.value = resultObj.articles

            }catch (e: Exception){
                _property.value = ArrayList()
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
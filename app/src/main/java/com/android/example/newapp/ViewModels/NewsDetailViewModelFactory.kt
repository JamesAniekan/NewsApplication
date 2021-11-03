package com.android.example.newapp.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.newapp.Article

class NewsDetailViewModelFactory(private val article: Article, private  val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsDetailViewModel::class.java)){
            return NewsDetailViewModel(article, application) as T
        }
        throw IllegalArgumentException("ViewModel not Found")
    }

}
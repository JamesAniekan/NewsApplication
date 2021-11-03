package com.android.example.newapp.ViewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.example.newapp.Article

class NewsDetailViewModel(@Suppress("UNUSED_PARAMETER")article: Article, application: Application) : ViewModel(){

    private val  _clickedProperty = MutableLiveData<Article>()
    val clickedProperty : LiveData<Article>
        get() = _clickedProperty

    init {
        _clickedProperty.value = article
    }
}
package com.android.example.newapp

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Url
import java.net.URI

data class NewsData(
     val status: String,
     val totalResults: Int,
     val articles: List<Article>)


@Parcelize
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?): Parcelable

@Parcelize
data class Source(
    val id: String?,
    val name: String): Parcelable

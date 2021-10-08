package com.android.example.newapp

import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://newsapi.org/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NewsApi{

    @GET("top-headlines?country=us&apiKey=afd189ebbb624d6f88abf8156dda7a6b")
    suspend fun getNewsProperties(): NewsData

    @GET("top-headlines?country=gb&apiKey=afd189ebbb624d6f88abf8156dda7a6b")
    suspend fun getUkNewsProperties(): NewsData

   @GET("top-headlines?category=sports&apiKey=afd189ebbb624d6f88abf8156dda7a6b")
    suspend fun getSportsNews(): NewsData

    @GET("top-headlines?country=ng&apiKey=afd189ebbb624d6f88abf8156dda7a6b")
    suspend fun getNgNews(): NewsData
}

object NewsObj {
    val newsService: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}




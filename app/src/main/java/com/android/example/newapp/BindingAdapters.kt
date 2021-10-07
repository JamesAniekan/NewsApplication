package com.android.example.newapp

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, urlToImage: String?){
   //if(urlToImage == null){
    //   imgView.setImageResource(R.drawable.ic_launcher_foreground)
   //}
   // else{
    urlToImage?.let{
        val imgUri = urlToImage.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)
  }
}

@BindingAdapter("listNews")
fun bindRecyclerView(rView:RecyclerView, article:List<Article>?){
    val adapter = rView.adapter as NewsAdapter
    adapter.submitList(article)
}
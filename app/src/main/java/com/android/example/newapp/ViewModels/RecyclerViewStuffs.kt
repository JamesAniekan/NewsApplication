package com.android.example.newapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.newapp.databinding.NewsItemBinding

class NewsViewHolder(private var news: NewsItemBinding): RecyclerView.ViewHolder(news.root){

    fun bind(item: Article){
        news.article = item
        news.executePendingBindings()
    }

    //Remember, this is like an inner class with its own function
    companion object{
        fun from(parent: ViewGroup): NewsViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = NewsItemBinding.inflate(layoutInflater, parent, false)
            return NewsViewHolder(binding)
        }
    }
}


class  NewsUtil : DiffUtil.ItemCallback<Article>(){
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}

class OnclickListener(val onclickListener: (article: Article)-> Unit){
    fun onclick(article: Article) = onclickListener(article)
}

class NewsAdapter(private val onclickListener: OnclickListener): ListAdapter<Article,NewsViewHolder>(NewsUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.itemView.setOnClickListener{
            onclickListener.onclick(article)
        }
        holder.bind(article)
    }
}

























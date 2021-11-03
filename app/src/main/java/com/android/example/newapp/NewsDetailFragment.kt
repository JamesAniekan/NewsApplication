package com.android.example.newapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailFragment : Fragment(R.layout.fragment_news_details) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val application = requireNotNull(activity).application
        // Inflate the layout for this fragment

        val article = com.android.example.newapp.NewsDetailFragmentArgs.fromBundle(requireArguments()).sentProperty

        detailView.apply { 
            webViewClient = WebViewClient()
             loadUrl(article.url)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "News Details"

    }

}



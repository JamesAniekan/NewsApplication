package com.android.example.newapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.example.newapp.Article
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailFragment : Fragment(R.layout.fragment_news_details) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val application = requireNotNull(activity).application
        // Inflate the layout for this fragment

        val article = NewsDetailFragmentArgs.fromBundle(requireArguments()).sentProperty

        detailView.apply { 
            webViewClient = WebViewClient()
             loadUrl(article.url)
        }



    }

}



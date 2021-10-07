package com.android.example.newapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.example.newapp.databinding.FragmentNewsDetailsBinding
import com.android.example.newapp.Article

class NewsDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val application = requireNotNull(activity).application
        // Inflate the layout for this fragment
        val binding:FragmentNewsDetailsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_news_details, container, false)
        binding.lifecycleOwner = this

        val article = NewsDetailFragmentArgs.fromBundle(requireArguments()).sentProperty

        val viewModelFactory = NewsDetailViewModelFactory(article, application)

        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(NewsDetailViewModel::class.java)


        return binding.root

    }

}



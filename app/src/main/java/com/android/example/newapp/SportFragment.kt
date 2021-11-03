package com.android.example.newapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.example.newapp.databinding.FragmentSportBinding


class SportFragment : Fragment() {

    private val viewModel: NewsViewModel by lazy { ViewModelProvider(this).get(NewsViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentSportBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_sport,
            container,
            false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.getSportsNews()

        val adapter = NewsAdapter(OnclickListener {
            viewModel.displayNewsDetail(it)
            //Toast.makeText(activity, it.title,Toast.LENGTH_LONG).show()
        })
        viewModel.navigateNewsDetail.observe(viewLifecycleOwner, {
            if(it != null) {
                this.findNavController().navigate(
                    com.android.example.newapp.SportFragmentDirections.actionSportFragmentToNewsDetailFragment3(
                        it
                    )
                )
                viewModel.doneNavigatingNewsDetail()
            }
        })

        binding.sportNews.adapter = adapter

        (activity as AppCompatActivity).supportActionBar?.title = "SPORTS HEADLINES"

        return binding.root
    }
}
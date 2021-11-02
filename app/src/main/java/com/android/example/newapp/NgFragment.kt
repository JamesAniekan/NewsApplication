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
import com.android.example.newapp.databinding.FragmentNgBinding


class NgFragment : Fragment() {

    private val viewModel: NewsViewModel by lazy { ViewModelProvider(this).get(NewsViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentNgBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_ng,container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.getNgNews()

        val adapter = NewsAdapter(OnclickListener {
            viewModel.displayNewsDetail(it)
            //Toast.makeText(activity, it.title,Toast.LENGTH_LONG).show()
        })

        viewModel.navigateNewsDetail.observe(viewLifecycleOwner, {
            if(it != null) {
                this.findNavController().navigate(NgFragmentDirections.actionNgFragmentToNewsDetailFragment3(it))
                viewModel.doneNavigatingNewsDetail()
            }
        })

        binding.ngNews.adapter = adapter

        (activity as AppCompatActivity).supportActionBar?.title = "NIGERIA"

        return binding.root

    }

}
package com.android.example.newapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.example.newapp.databinding.FragmentUkBinding

class UkFragment : Fragment() {

    private val viewModel: NewsViewModel by lazy { ViewModelProvider(this).get(NewsViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentUkBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_uk,container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.getUkNews()

        val adapter = NewsAdapter(OnclickListener {
            viewModel.displayNewsDetail(it)
            //Toast.makeText(activity, it.title,Toast.LENGTH_LONG).show()
            // this.findNavController().navigate(R.id.action_newsFragment7_to_newsDetailFragment3)
        })
        viewModel.navigateNewsDetail.observe(viewLifecycleOwner, {
           if(it != null) {
                this.findNavController().navigate(NewsFragmentDirections.actionNewsFragment7ToNewsDetailFragment3(it))
              viewModel.doneNavigatingNewsDetail()
            }
        })
        binding.healthNews.adapter = adapter


        return binding.root
    }
}


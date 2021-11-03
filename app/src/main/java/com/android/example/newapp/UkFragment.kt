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
import com.android.example.newapp.databinding.FragmentUkBinding

class UkFragment : Fragment() {

    private val viewModel: NewsViewModel by lazy { ViewModelProvider(this).get(NewsViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentUkBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_uk,container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        /**
        On navigation to this fragment this viewmodel's function is called which triggers a network call to UK news endpoint.
         The viewmodel's property LiveData list is populated and displayed on the recyclerview.
         This pattern is followed across all fragments except the NavHostFragment.
         */
        viewModel.getUkNews()

        val adapter = NewsAdapter(OnclickListener {
            viewModel.displayNewsDetail(it)
            //Toast.makeText(activity, it.title,Toast.LENGTH_LONG).show()
        })
        viewModel.navigateNewsDetail.observe(viewLifecycleOwner, {
           if(it != null) {
                this.findNavController().navigate(
                    com.android.example.newapp.UkFragmentDirections.actionUkFragmentToNewsDetailFragment3(
                        it
                    )
                )
              viewModel.doneNavigatingNewsDetail()
            }
        })

        binding.healthNews.adapter = adapter

        (activity as AppCompatActivity).supportActionBar?.title = "UK HEADLINES"

        return binding.root
    }
}


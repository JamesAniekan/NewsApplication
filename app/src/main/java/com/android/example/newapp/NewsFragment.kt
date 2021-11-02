package com.android.example.newapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.example.newapp.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by lazy { ViewModelProvider(this).get(NewsViewModel::class.java)}

    override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val binding:FragmentNewsBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_news,container , false)
        /**
         * Set fragment as binding lifecycle owner.
         */
        binding.lifecycleOwner = this

        binding.viewModel = viewModel



        /**Adapter's click listener passes Article to viewmodel's navigateNewsDetail through
         * displayNewsDetail function **/
        val adapter = NewsAdapter(OnclickListener {
            viewModel.displayNewsDetail(it)
            //Toast.makeText(activity, it.title,Toast.LENGTH_LONG).show()
           // this.findNavController().navigate(R.id.action_newsFragment7_to_newsDetailFragment3)
            })

        /**Observe LiveData variable of Article type and pass it to the NewsDetail fragment using the generated FragmentDirections
         * class. Disconnect navigation when done.
         **/
        viewModel.navigateNewsDetail.observe(viewLifecycleOwner, {
            if(it != null) {
                this.findNavController().navigate(NewsFragmentDirections.actionNewsFragment7ToNewsDetailFragment3(it))
                viewModel.doneNavigatingNewsDetail()
            }
        })

        /**
         * Observe networkState LiveData and show progress bar and text depending on state.
         */
        viewModel.networkState.observe(viewLifecycleOwner, {
            when(it){
                NetworkState.LOADING -> binding.loadingProgressBar.visibility = View.VISIBLE
                NetworkState.SUCCESS -> binding.loadingProgressBar.visibility = View.GONE
                NetworkState.ERROR -> {
                    binding.apply {
                        errorText.text = getString(R.string.errorMsg)
                        errorText.visibility = View.VISIBLE
                        loadingProgressBar.visibility = View.GONE
                    }
                }
            }
        })

        binding.newsList.adapter = adapter


         return binding.root
    }

}


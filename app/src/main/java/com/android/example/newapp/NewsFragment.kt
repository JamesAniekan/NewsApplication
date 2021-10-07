package com.android.example.newapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.example.newapp.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

 private val viewModel: NewsViewModel by lazy { ViewModelProvider(this).get(NewsViewModel::class.java)}

    override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
       savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val binding:FragmentNewsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_news,container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

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
        binding.newsList.adapter = adapter
        setHasOptionsMenu(true)

         return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.over_flow, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.uk_news -> viewModel.getUkNews()
            R.id.sports -> viewModel.getSportsNews()
        }
        return true
    }
}


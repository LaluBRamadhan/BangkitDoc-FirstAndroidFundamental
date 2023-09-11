package com.code.submissionawalfundamental.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.submissionawalfundamental.databinding.FragmentFollowingBinding
import com.code.submissionawalfundamental.ui.activity.DetailProfile
import com.code.submissionawalfundamental.ui.adapter.GithubAdapter
import com.code.submissionawalfundamental.ui.viewmodel.FollowingViewModel

class FollowingFragment : Fragment() {

    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: GithubAdapter
    private var _binding: FragmentFollowingBinding? = null
    private val binding get()  = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val argument = arguments
        val username = argument?.getString(DetailProfile.EXTRA_NAME).toString()

        _binding = FragmentFollowingBinding.inflate(inflater, container,false)

        val linearLayout = LinearLayoutManager(this.context)
        binding.rvFollowing.layoutManager = linearLayout

        adapter = GithubAdapter()
        adapter.notifyDataSetChanged()
        binding.rvFollowing.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.getFollowing(username)

        viewModel.listFollowing().observe(viewLifecycleOwner){
            adapter.submit(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
        return binding.root
    }
    private fun showLoading(isLoading: Boolean){
        if(!isLoading){
            binding.progressBar.visibility = View.INVISIBLE
        }else{
            binding.progressBar.visibility = View.VISIBLE
        }
    }
}
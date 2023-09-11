package com.code.submissionawalfundamental.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.submissionawalfundamental.databinding.FragmentFollowerBinding
import com.code.submissionawalfundamental.ui.activity.DetailProfile
import com.code.submissionawalfundamental.ui.adapter.GithubAdapter
import com.code.submissionawalfundamental.ui.viewmodel.FollowerViewModel

class FollowerFragment : Fragment() {
    private lateinit var viewModel: FollowerViewModel
    private lateinit var adapter: GithubAdapter
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = arguments
        val username = args?.getString(DetailProfile.EXTRA_NAME).toString()

        _binding = FragmentFollowerBinding.inflate(inflater, container, false)

        val linearLayout = LinearLayoutManager(requireActivity())
        binding.rvFollower.layoutManager = linearLayout

        adapter = GithubAdapter()
        adapter.notifyDataSetChanged()
        binding.rvFollower.adapter = adapter


        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowerViewModel::class.java)
        viewModel.getFollower(username)
        Log.e("hello", "onCreateView: $username")

        viewModel.listFollower().observe(viewLifecycleOwner){
            adapter.submit(it)
        }
        return binding.root
    }

}


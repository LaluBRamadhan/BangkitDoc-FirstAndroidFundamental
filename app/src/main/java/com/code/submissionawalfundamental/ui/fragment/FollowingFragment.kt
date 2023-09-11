package com.code.submissionawalfundamental.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.code.submissionawalfundamental.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {


    private var _binding: FragmentFollowingBinding? = null
    private val binding get()  = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container,false)
        return binding.root
    }

}
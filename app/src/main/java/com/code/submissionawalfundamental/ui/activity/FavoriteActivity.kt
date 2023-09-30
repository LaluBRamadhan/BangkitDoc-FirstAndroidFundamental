package com.code.submissionawalfundamental.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.submissionawalfundamental.database.FavoriteUser
import com.code.submissionawalfundamental.databinding.ActivityFavoriteBinding
import com.code.submissionawalfundamental.repository.FavoriteRepository
import com.code.submissionawalfundamental.ui.adapter.FavoriteAdapter
import com.code.submissionawalfundamental.ui.viewmodel.FavoriteViewModel
import com.code.submissionawalfundamental.ui.viewmodel.FavoriteViewModelFactory

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = FavoriteRepository(application)
        val viewModelFactory = FavoriteViewModelFactory(application, repository)
        favoriteViewModel = ViewModelProvider(this, viewModelFactory).get(FavoriteViewModel::class.java)

        favoriteViewModel.repo().observe(this) {
            setDataFavorite(it)
        }
    }

    private fun setDataFavorite(user: List<FavoriteUser>) {
        val adapter = FavoriteAdapter()
        adapter.setListUser(user)
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
    }

}
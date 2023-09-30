package com.code.submissionawalfundamental.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.code.submissionawalfundamental.database.FavoriteUser
import com.code.submissionawalfundamental.repository.FavoriteRepository

class FavoriteViewModel(application: Application, private val repository: FavoriteRepository) : ViewModel() {

    fun repo(): LiveData<List<FavoriteUser>> {
        return repository.getAllFavorite()
    }
}
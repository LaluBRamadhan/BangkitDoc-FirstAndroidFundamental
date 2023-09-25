package com.code.submissionawalfundamental.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.code.submissionawalfundamental.database.FavoriteDao
import com.code.submissionawalfundamental.database.FavoriteDatabase
import com.code.submissionawalfundamental.database.FavoriteUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorite(): LiveData<List<FavoriteUser>> = mFavoriteDao.getAllFavorite()

    fun getUserByUsername(username: String): FavoriteUser? {
        return mFavoriteDao.getUserByUsername(username)
    }

    fun insert(fav: FavoriteUser){
        executorService.execute { mFavoriteDao.insertUser(fav) }
    }

    fun delete(fav: FavoriteUser){
        executorService.execute { mFavoriteDao.deleteUser(fav) }
    }
}
package com.code.submissionawalfundamental.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.code.submissionawalfundamental.data.response.DetailUserResponse
import com.code.submissionawalfundamental.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): AndroidViewModel(application) {

    private val _githubDetail = MutableLiveData<DetailUserResponse>()
    val githubDetail:LiveData<DetailUserResponse> = _githubDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    private var favoriteDao: FavoriteDao?
//    private var db: FavoriteDatabase?

    init {
//        db =FavoriteDatabase.getDatabase(application)
//        favoriteDao = db?.favoriteDao()
        githubDetail("")
    }

    fun githubDetail(username:String){
        _isLoading.value = true
        val detail = ApiConfig.getApiService().getDetailUser(username)
        detail.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _githubDetail.value = response.body()
                    Log.e("anying", "onResponse: ${username}", )

                }else{
                    Log.e("FailedGetGithubUserDetail", "onResponse: ${response.message()}", )
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("FailedGetGithubUserDetail", "onFailure: ${t.message.toString()}", )
            }

        })
    }
//
//    fun add(id: Int, username: String){
//        CoroutineScope(Dispatchers.IO).launch {
//            var user = FavoriteUser(
//                id,
//                username
//            )
//            favoriteDao?.insertUser(user)
//        }
//    }
//
//    suspend fun check(id: Int) = favoriteDao?.check(id)
//
//    suspend fun delete(id: Int){
//        CoroutineScope(Dispatchers.IO).launch {
//
//        }
//    }
}
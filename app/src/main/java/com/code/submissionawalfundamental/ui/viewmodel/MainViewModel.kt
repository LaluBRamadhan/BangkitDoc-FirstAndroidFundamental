package com.code.submissionawalfundamental.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.code.submissionawalfundamental.data.response.GithubResponse
import com.code.submissionawalfundamental.data.response.ItemsItem
import com.code.submissionawalfundamental.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val _githubUser = MutableLiveData<List<ItemsItem>>()
    val githubResponse:LiveData<List<ItemsItem>> = _githubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        usernameGithub("mark")
    }

    fun usernameGithub(Query:String){
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUser(Query)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value =false

                if (response.isSuccessful){
                    _githubUser.value = response.body()?.items
                }else{
                    Log.e("FailedGetGithubUser", "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("FailedGetGithubUser", "onFailure: ${t.message.toString()}", )
            }
        })
    }


}
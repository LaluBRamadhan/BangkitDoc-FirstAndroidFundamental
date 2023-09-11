package com.code.submissionawalfundamental.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.code.submissionawalfundamental.data.response.ItemsItem
import com.code.submissionawalfundamental.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {
    private val _followingDetail = MutableLiveData<List<ItemsItem>>()
    val followingDetal: LiveData<List<ItemsItem>> = _followingDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getFollowing(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<ItemsItem>>{
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false

                if (response.isSuccessful){
                    _followingDetail.value = response.body()
                }else{
                    Log.e("Fail", "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e("Fail", "onFailure: ${t.message.toString()}", )
            }
        })
    }

    fun listFollowing(): LiveData<List<ItemsItem>>{
        return followingDetal
    }
}
package com.example.navigationcomponent.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationcomponent.MyApplication
import com.example.navigationcomponent.api.ApiService
import com.example.navigationcomponent.model.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstViewModel: ViewModel() {

    var countries = MutableLiveData<ArrayList<Country>>()

    fun fetchCountries() {
        val apiService = MyApplication.instance.getApiService()!!
        apiService.getCountries().enqueue(object : Callback<ArrayList<Country>> {
            override fun onFailure(call: Call<ArrayList<Country>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<Country>>,
                response: Response<ArrayList<Country>>
            ) {
                Log.d("Response", response.body()?.size.toString())
                countries.value = response.body()!!
            }
        })
    }
}
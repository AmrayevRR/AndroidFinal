package com.example.navigationcomponent.api

import com.example.navigationcomponent.model.Country
import com.example.navigationcomponent.model.Stat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("countries")
    fun getCountries(): Call<ArrayList<Country>>

    @GET("country/{slug}")
    fun getStats(@Path("slug") slug: String): Call<ArrayList<Stat>>
}
package com.weather.api

import retrofit2.Call
import com.weather.model.WeatherModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    private val KEY_API: String
        get() = "c56873ca779c4f20ad1124010220109"

    @GET("/forecast.json?")
    fun getWeather(
        @Query("key") key: String = KEY_API,
        @Query("q") q: String,
    )
            : Call<List<WeatherModel>>


}
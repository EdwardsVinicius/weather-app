package com.weather.api

import retrofit2.Call
import com.weather.model.WeatherModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    private val KEY_API: String
        get() = "6dbe6d0a6b5e4a0e84b122337222009"

    @GET("v1/forecast.json")
    fun getWeather(
        @Query("key") key: String = KEY_API,
        @Query("q") q: String,
//        @Query("lang") lang: String = "pt"
    )
            : Call<WeatherModel>


}
package com.weather.api

import retrofit2.Call
import com.weather.ui.model.WeatherModel
import retrofit2.http.*

interface APIService {
    private val KEY_API: String
        get() = "6dbe6d0a6b5e4a0e84b122337222009"

    @GET("forecast.json")
    fun getWeather(
        @Query("key") key: String = KEY_API,
        @Query("q") q: String,
        @Query("days") days: Int = 7
//        @Query("lang") lang: String = "pt"
    )
            : Call<WeatherModel>


}
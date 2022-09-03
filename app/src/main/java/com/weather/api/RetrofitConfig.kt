package com.weather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object{
        private const val baseUrl = "http://api.weatherapi.com/"

        fun getRetrofitInstance(path: String = baseUrl): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}
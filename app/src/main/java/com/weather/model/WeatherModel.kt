package com.weather.model

import com.google.gson.annotations.SerializedName


data class WeatherModel(
    @SerializedName("name")
    var cityName: String,
    @SerializedName("localtime")
    var time: String,
    @SerializedName("temp_c")
    var temperature: String,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("wind_kph")
    var windSpeed: String,
)

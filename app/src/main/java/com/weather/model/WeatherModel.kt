package com.weather.model

import android.service.notification.Condition
import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("location")
    var location: LocationData,
    @SerializedName("current")
    var current: CurrentData
)

data class LocationData(
    @SerializedName("name")
    var cityName: String,
    @SerializedName("localtime")
    var time: String

)

data class CurrentData(
    @SerializedName("temp_c")
    var temperature: String,
    @SerializedName("condition")
    var condition: ConditionData,
    @SerializedName("wind_kph")
    var windSpeed: String
)

data class ConditionData(
    @SerializedName("icon")
    var icon: String
)

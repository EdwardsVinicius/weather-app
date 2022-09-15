package com.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("location")
    var location: LocationData,
    @SerializedName("current")
    var current: CurrentData,
    @SerializedName("forecast")
    var forecast: ForecastData
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
    @SerializedName("is_day")
    var isDay: Int,
    @SerializedName("condition")
    var condition: ConditionData,
    @SerializedName("wind_kph")
    var windSpeed: String
)

data class ConditionData(
    @SerializedName("icon")
    var icon: String,
    @SerializedName("text")
    var conditionText: String
)

data class ForecastData(
    @SerializedName("forecastday")
    var forecastDayArray: List<ForecastDayArray>
)

data class ForecastDayArray(
    @SerializedName("hour")
    var hourArray: List<HourArrayData>
)

data class HourArrayData(
    @SerializedName("temp_c")
    var temperature: String,
    @SerializedName("condition")
    var condition: ConditionData,
    @SerializedName("wind_kph")
    var windSpeed: String
)
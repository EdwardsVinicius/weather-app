package com.weather.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "temperature") val temperature: String,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "region") val region: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "hour") val hour: String,

    )

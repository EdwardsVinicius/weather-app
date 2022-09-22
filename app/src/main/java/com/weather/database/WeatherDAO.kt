package com.weather.database

import androidx.room.*

@Dao
interface WeatherDAO {
    @Query("SELECT * FROM weather") fun getAllUsers(): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertUsers(vararg users: WeatherEntity)

    @Update
    fun updateWeather(weatherEntity: WeatherEntity)

    @Delete
    fun deleteWeather(weatherEntity: WeatherEntity)
}
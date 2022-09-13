package com.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.squareup.picasso.Picasso
import com.weather.adapters.RecyclerViewAdapter
import com.weather.api.APIService
import com.weather.api.RetrofitConfig
import com.weather.databinding.ActivityMainBinding
import com.weather.model.WeatherModel
import okhttp3.ResponseBody
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var cityName: TextView
    private lateinit var temperature: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var weatherCondition: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var background: ImageView
    private var weatherModelList: ArrayList<WeatherModel> = arrayListOf()

    private val adapter = RecyclerViewAdapter(
        context = this,
        weatherList = weatherModelList.toList()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        cityName = binding.activityMainCityName
        temperature = binding.activityMainTemperature
        weatherIcon = binding.activityMainWeatherIcon
        weatherCondition = binding.activityMainWeatherCondition
        recyclerView = binding.activityMainRecyclerView
        background = binding.activityMainBackground

        getWeather()
        Log.i("ADAPTER", weatherModelList.toString())
        recyclerView.adapter = adapter

    }

    fun getWeather(){
        val retrofitClient = RetrofitConfig.getRetrofitInstance()
        val service = retrofitClient.create(APIService::class.java)
        val callback = service.getWeather(q = "João")

        callback.enqueue(object : Callback<WeatherModel> {
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                Log.e("RETROFIT_ERROR", t.message.toString())
            }

            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                Log.i("RETROFIT_DEBUG", response.body().toString())
                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(
                        response.body()
                    )


                Log.d("Printed JSON :", prettyJson)
                var weatherModel: WeatherModel? = response.body()
                if (weatherModel != null) {
                    cityName.text = weatherModel.location.cityName
                    Picasso.get().load("http:" + weatherModel.current.condition.icon).into(weatherIcon)
                    temperature.text = weatherModel.current.temperature.toDouble().toInt().toString() + "°C"
                    weatherCondition.text = weatherModel.current.condition.conditionText
                    var hourArray = weatherModel.forecast.forecastDayArray[0].hourArray

                    if (weatherModel.current.isDay == 1)
                        Picasso.get()
                            .load("")
                            .into(background)
                    else
                        Picasso.get()
                            .load("https://images.unsplash.com/photo-1533206601904-1a399c3479ce?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80")
                            .into(background)

                    for (i in 0..hourArray.size){
                        weatherModelList.add(weatherModel)
                    }
                }

            }
        })
    }
}
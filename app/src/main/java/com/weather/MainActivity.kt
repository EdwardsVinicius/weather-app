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

//    private val adapter = RecyclerViewAdapter(
//        context = this,
//        weatherList = List<WeatherModel>
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        cityName = binding.activityMainCityName
        temperature = binding.activityMainTemperature
        weatherIcon = binding.activityMainWeatherIcon
        weatherCondition = binding.activityMainWeatherCondition
        recyclerView = binding.activityMainRecyclerView
//        recyclerView.adapter = adapter

        getWeather()

    }

    fun getWeather(){
        val retrofitClient = RetrofitConfig.getRetrofitInstance()
        val service = retrofitClient.create(APIService::class.java)
        val callback = service.getWeather(q = "Manaus")

        callback.enqueue(object : Callback<List<WeatherModel>> {
            override fun onFailure(call: Call<List<WeatherModel>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                Log.e("RETORFIT_ERROR", t.message.toString())
            }

            override fun onResponse(call: Call<List<WeatherModel>>, response: Response<List<WeatherModel>>) {
                Log.i("RETORFIT_DEBUG", response.toString())
                response.body()?.forEach {
                    temperature.text = temperature.text.toString()//.plus(it.body)
                    weatherCondition.text = weatherCondition.text.toString()
//                    Picasso.get().load(url).into(weatherIcon);
                }
            }
        })
    }
}
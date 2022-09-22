package com.weather.ui.view

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import com.weather.R
import com.weather.ui.adapters.RecyclerViewAdapter
import com.weather.api.APIService
import com.weather.api.RetrofitConfig
import com.weather.databinding.ActivityMainBinding
import com.weather.ui.model.ForecastDayArray
import com.weather.ui.model.LocationData
import com.weather.ui.model.WeatherModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var progressBar: ProgressBar
    private lateinit var cityName: TextView
    private lateinit var inputCity: EditText
    private lateinit var imageButton: ImageButton
    private lateinit var temperature: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var weatherCondition: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var background: ImageView
    private var weatherModelList: ArrayList<ForecastDayArray> = arrayListOf()

    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
//            this, R.layout.activity_main)


        progressBar = binding.activityMainProgressBar
        cityName = binding.activityMainCityName
        inputCity = binding.activityMainEditText
        imageButton = binding.activityMainImageButton
        temperature = binding.activityMainTemperature
        weatherIcon = binding.activityMainWeatherIcon
        weatherCondition = binding.activityMainWeatherCondition
        recyclerView = binding.activityMainRecyclerView
        background = binding.activityMainBackground

//        val locationManager = getSystemService(Context.LOCATION_SERVICE)
//        if (ActivityCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//        }


        imageButton.setOnClickListener {
            if (inputCity != null) {
                progressBar.visibility = View.VISIBLE
                getWeather(inputCity.text.toString())
                this.currentFocus?.let { view ->
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(view.windowToken, 0)
                }
            } else {
                Toast.makeText(baseContext, "City Not Found", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun getWeather(local: String) {


        val retrofitClient = RetrofitConfig.getRetrofitInstance()
        val service = retrofitClient.create(APIService::class.java)
        val callback = service.getWeather(q = local)

        callback.enqueue(object : Callback<WeatherModel> {
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
//                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
//                Log.e("RETROFIT_ERROR", t.message.toString())
            }

            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                Log.i("RETROFIT_DEBUG", response.body().toString())
                progressBar.visibility = GONE

                if (response.code() == 200) {
                    var weatherModel: WeatherModel? = response.body()
                    if (weatherModel != null) {
//                    binding.locationData =
//                        LocationData(
//                            weatherModel.location.cityName,
//                            weatherModel.location.region,
//                            weatherModel.location.time
//                        )

                        cityName.text = weatherModel.location.cityName
                        Picasso.get().load("http:" + weatherModel.current.condition.icon)
                            .into(weatherIcon)
                        temperature.text =
                            weatherModel.current.temperature.toDouble().toInt().toString() + "°C"
                        weatherCondition.text = weatherModel.current.condition.conditionText
                        var forecastDayArray = weatherModel.forecast.forecastDayArray

                        if (weatherModel.current.isDay == 1)
                            Picasso.get()
                                .load("https://images.unsplash.com/photo-1556772219-94f40b530c1c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1964&q=80")
                                .into(background)
                        else
                            Picasso.get()
                                .load("https://images.unsplash.com/photo-1533206601904-1a399c3479ce?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80")
                                .into(background)
                        background.alpha = 0.5F

                        for (e in forecastDayArray) {
                            weatherModelList.add(e)
                        }
                        Log.i("DEBUG_LIST 2", weatherModelList.toString())
                        adapter = RecyclerViewAdapter(weatherModelList)
                        recyclerView.adapter = adapter
                    }
                } else
                    Toast.makeText(baseContext, "City Not Found", Toast.LENGTH_LONG).show()


            }
        })
    }

    fun function(context: Context) {

    }
}
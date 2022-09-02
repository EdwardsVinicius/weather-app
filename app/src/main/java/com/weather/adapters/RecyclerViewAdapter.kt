package com.weather.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weather.databinding.WeatherRecyclerViewItemBinding
import com.weather.model.WeatherModel

class RecyclerViewAdapter(
    private val context: Context,
    weatherList: List<WeatherModel>,
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val weatherList = weatherList.toMutableList()

    class ViewHolder(
        private val binding: WeatherRecyclerViewItemBinding,
        weatherList: List<WeatherModel>,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        val time = binding.recyclerViewItemTime
        val temperature = binding.recyclerViewItemTemp
        val icon = binding.recyclerViewWeatherItemIcon
        val windSpeed = binding.recyclerViewWeatherItemCond

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = WeatherRecyclerViewItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding, weatherList = weatherList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherModel = weatherList[position]

        holder.temperature.text = weatherModel.temperature + "°C"
        Picasso.get().load("http:" + weatherModel.icon).into(holder.icon)
        holder.windSpeed.text = weatherModel.windSpeed + "Km/h"

    }

    override fun getItemCount() = weatherList.size

}
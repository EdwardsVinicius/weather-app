package com.weather.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weather.databinding.WeatherRecyclerViewItemBinding
import com.weather.model.HourArrayData
import com.weather.model.WeatherModel

class RecyclerViewAdapter(
    weatherList: List<HourArrayData>,
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val weatherList = weatherList.toMutableList()

    class ViewHolder(
        private val binding: WeatherRecyclerViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

//        val time = binding.recyclerViewItemTime
        var temperature: TextView
        var icon: ImageView
        var windSpeed: TextView

        init {
            temperature = binding.recyclerViewItemTemp
            icon = binding.recyclerViewWeatherItemIcon
            windSpeed = binding.recyclerViewWeatherItemCond
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WeatherRecyclerViewItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherModel = weatherList[position]

        holder.temperature.text = weatherModel.temperature + "°C"
        Picasso.get().load("http:" + weatherModel.condition.icon).into(holder.icon)
        holder.windSpeed.text = weatherModel.windSpeed + "Km/h"

    }

    override fun getItemCount() = weatherList.size

}
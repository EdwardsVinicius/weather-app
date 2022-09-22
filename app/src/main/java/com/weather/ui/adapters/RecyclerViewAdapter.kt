package com.weather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weather.databinding.WeatherRecyclerViewItemBinding
import com.weather.ui.model.ForecastDayArray
import java.util.*

class RecyclerViewAdapter(
    weatherList: List<ForecastDayArray>,
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val weatherList = weatherList.toMutableList()

    class ViewHolder(
        view: WeatherRecyclerViewItemBinding,
    ) :
        RecyclerView.ViewHolder(view.root) {

        val weekday = view.recyclerViewItemWeekDay
        var maxTemp = view.recyclerViewItemMaxTemp
        var minTemp = view.recyclerViewItemMinTemp
        var icon = view.recyclerViewItemIcon
        var condition = view.recyclerViewItemCondition

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WeatherRecyclerViewItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherModel = weatherList[position]

        holder.weekday.text = getDayOfWeek(weatherModel, position)
        holder.maxTemp.text = weatherModel.dayData.maxTemp + "°C"
        holder.minTemp.text = weatherModel.dayData.minTemp + "°C"
        Picasso.get().load("http:" + weatherModel.dayData.condition.icon).into(holder.icon)
        holder.condition.text = weatherModel.dayData.condition.conditionText


    }

    override fun getItemCount() = weatherList.size

    fun getDayOfWeek(weatherModel: ForecastDayArray, position: Int): String {
        var date: Date = weatherModel.date
        val c = Calendar.getInstance()
        c.time = date

        val weekString = when(c.get(Calendar.DAY_OF_WEEK)) {
            1 -> "Sun"
            2 -> "Mon"
            3 -> "Tue"
            4 -> "Wed"
            5 -> "Thu"
            6 -> "Fri"
            7 -> "Sat"
            else -> "Invalid day"
        }

        return weekString
    }

}
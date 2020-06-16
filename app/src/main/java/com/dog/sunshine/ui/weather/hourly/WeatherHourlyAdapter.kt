package com.dog.sunshine.ui.weather.hourly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.databinding.ItemWeatherHourlyBinding

class WeatherHourlyAdapter(
    private val listWeather: List<Current>
): RecyclerView.Adapter<WeatherHourlyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHourlyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemWeatherHourlyBinding: ItemWeatherHourlyBinding = ItemWeatherHourlyBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return WeatherHourlyViewHolder(
            itemWeatherHourlyBinding
        )
    }

    override fun getItemCount(): Int {
        return listWeather.size
    }

    override fun onBindViewHolder(holder: WeatherHourlyViewHolder, position: Int) {
        val current: Current = listWeather[position]
        holder.bindData(current)
    }

}
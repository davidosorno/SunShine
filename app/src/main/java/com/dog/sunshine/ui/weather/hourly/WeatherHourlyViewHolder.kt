package com.dog.sunshine.ui.weather.hourly

import androidx.recyclerview.widget.RecyclerView
import com.dog.sunshine.data.weather.CurrentWeather
import com.dog.sunshine.databinding.ItemWeatherHourlyBinding

class WeatherHourlyViewHolder(val binding: ItemWeatherHourlyBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(currentWeather: CurrentWeather) {
        binding.hourly = currentWeather
        binding.executePendingBindings()
    }
}
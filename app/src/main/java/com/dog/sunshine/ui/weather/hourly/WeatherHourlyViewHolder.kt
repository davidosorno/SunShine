package com.dog.sunshine.ui.weather.hourly

import androidx.recyclerview.widget.RecyclerView
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.databinding.ItemWeatherHourlyBinding

class WeatherHourlyViewHolder(val binding: ItemWeatherHourlyBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(current: Current) {
        binding.current = current
        binding.executePendingBindings()
    }

}
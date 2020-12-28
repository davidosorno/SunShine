package com.dog.sunshine.ui.weather.daily

import androidx.recyclerview.widget.RecyclerView
import com.dog.sunshine.data.weather.CurrentWeather
import com.dog.sunshine.databinding.ItemWeatherDailyBinding


class DailyWeatherViewHolder(val binding: ItemWeatherDailyBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(currentWeather: CurrentWeather, clickListener: (CurrentWeather) -> Unit){
        binding.daily = currentWeather
        itemView.setOnClickListener {
            clickListener(currentWeather)
        }
        binding.executePendingBindings()
    }
}
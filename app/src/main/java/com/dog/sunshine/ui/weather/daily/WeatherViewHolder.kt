package com.dog.sunshine.ui.weather.daily

import androidx.recyclerview.widget.RecyclerView
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.databinding.ItemWeatherDailyBinding


class WeatherViewHolder(val binding: ItemWeatherDailyBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(current: Current, clickListener: (Current) -> Unit){
        binding.weather = current
        itemView.setOnClickListener {
            clickListener(current)
        }
        binding.executePendingBindings()
    }
}
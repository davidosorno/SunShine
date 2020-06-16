package com.dog.sunshine.ui.weather

import androidx.recyclerview.widget.RecyclerView
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.databinding.ItemWeatherBinding


class WeatherViewHolder(val binding: ItemWeatherBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(current: Current, clickListener: (Current) -> Unit){
        binding.weather = current
        itemView.setOnClickListener {
            clickListener(current)
        }
        binding.executePendingBindings()
    }
}
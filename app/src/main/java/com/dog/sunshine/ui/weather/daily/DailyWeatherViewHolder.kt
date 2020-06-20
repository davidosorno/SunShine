package com.dog.sunshine.ui.weather.daily

import androidx.recyclerview.widget.RecyclerView
import com.dog.sunshine.data.networkservice.Daily
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.databinding.ItemWeatherDailyBinding


class DailyWeatherViewHolder(val binding: ItemWeatherDailyBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindData(current: Current, clickListener: (Current) -> Unit){
        binding.daily = current
        itemView.setOnClickListener {
            clickListener(current)
        }
        binding.executePendingBindings()
    }
}
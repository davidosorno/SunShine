package com.dog.sunshine.ui.weather.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.databinding.ItemWeatherDailyBinding

class WeatherAdapter(
    private val clickListener: (Current) -> Unit
): PagedListAdapter<Current, WeatherViewHolder>(
    DIFF_CALLBACK
) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Current>() {
            override fun areItemsTheSame(oldItem: Current, newItem: Current): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: Current, newItem: Current): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemWeatherBinding: ItemWeatherDailyBinding = ItemWeatherDailyBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return WeatherViewHolder(
            itemWeatherBinding
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current: Current? = getItem(position)
        current?.let {
            holder.bindData(
                it,
                clickListener
            )
        }
    }
}
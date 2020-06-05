package com.dog.sunshine.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dog.sunshine.R
import com.dog.sunshine.data.weather.Weather

class WeatherAdapter(
    private val clickListener: (Weather) -> Unit
): PagedListAdapter<Weather, WeatherViewHolder>(
    DIFF_CALLBACK
) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Weather>() {
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.item_weather,
            parent,
            false
        )
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather: Weather? = getItem(position)
        weather?.let {
            holder.bindData(
                it,
                holder.itemView.context,
                position,
                clickListener
            )
        }
    }


}
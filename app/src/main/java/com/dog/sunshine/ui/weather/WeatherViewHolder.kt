package com.dog.sunshine.ui.weather

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dog.sunshine.R
import com.dog.sunshine.data.weather.Weather
import kotlinx.android.synthetic.main.fragment_detail_weather.view.*
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindData(weather: Weather, context: Context, position: Int, clickListener: (Weather) -> Unit){

        itemView.text_day.text = context.resources.getString(R.string.concatExample, position.toString(), weather.date.toString())
        itemView.weather_description.text = weather.weatherId.toString()
        itemView.high_temperature.text = weather.max.toString()

        itemView.setOnClickListener {
            clickListener(weather)
        }
    }
}
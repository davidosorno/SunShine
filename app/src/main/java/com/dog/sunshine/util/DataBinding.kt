package com.dog.sunshine.util

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.dog.sunshine.R
import com.dog.sunshine.data.weather.Weather
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setImage")
fun ImageView.image(weather: Weather){
    setImageDrawable(context.getDrawable(R.drawable.art_clear))
}

@BindingAdapter("setDay")
fun AppCompatTextView.day(weather: Weather){
    //TODO get location and create a variable Locale to change here
    val formatter = SimpleDateFormat("DD", Locale.US)
    val date = Calendar.getInstance()
    date.timeInMillis = weather.date
    text = formatter.format(date).toUpperCase(Locale.US)
}

@BindingAdapter("setDescription")
fun AppCompatTextView.description(weather: Weather){
    text = resources.getString(R.string.phWeatherDescription)
}

@BindingAdapter("setHighTemperature")
fun AppCompatTextView.highTemperature(weather: Weather){
    text = weather.max.toString()
}

@BindingAdapter("setLowTemperature")
fun AppCompatTextView.lowTemperature(weather: Weather){
    text = weather.min.toString()
}

@BindingAdapter("setHumidity")
fun AppCompatTextView.humidity(weather: Weather){
    text = weather.humidity.toString()
}

@BindingAdapter("setHighPressure")
fun AppCompatTextView.pressure(weather: Weather){
    text = weather.pressure.toString()
}

@BindingAdapter("setWind")
fun AppCompatTextView.wind(weather: Weather){
    text = weather.wind.toString()
}
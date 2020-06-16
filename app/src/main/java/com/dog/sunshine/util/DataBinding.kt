package com.dog.sunshine.util

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.dog.sunshine.R
import com.dog.sunshine.data.weather.current.Current
import java.util.*

@BindingAdapter("setDay")
fun AppCompatTextView.day(current: Current?){
    text = resources.getString(
        R.string.date,
        setFormatDate(current?.date, "EEE, MMM dd yyyy")
    )
}

@BindingAdapter("setDayAndHour")
fun AppCompatTextView.dayAndHour(current: Current?){
    text = resources.getString(
        R.string.date,
        setFormatDate(current?.date, "EEE, MMM dd yyyy HH:mm")
    )
}

@BindingAdapter("setImage")
fun ImageView.image(current: Current?){
    current?.let {
        val iconId = current.icon.substring(0, 2)
        setImageDrawable(context.getDrawable(
            when(iconId){
                "01" -> R.drawable.art_clear // clear sky
                "02" -> R.drawable.art_light_clouds // few clouds
                "03" -> R.drawable.art_clouds // scattered clouds
                "04" -> R.drawable.art_light_clouds // broken clouds
                "09" -> R.drawable.art_light_rain // broken clouds
                "10" -> R.drawable.art_rain // shower rain
                "11" -> R.drawable.art_storm // thunderstorm
                "13" -> R.drawable.art_snow // snow
                "50" -> R.drawable.art_fog // mist
                else -> R.drawable.common_full_open_on_phone
            })
        )
    }
}

@ExperimentalStdlibApi
@BindingAdapter("setDescription")
fun AppCompatTextView.description(current: Current?){
    text = resources.getString(R.string.weatherDescription, current?.description?.capitalize(Locale.US) ?: "")
}

@BindingAdapter("setMaxTemp")
fun AppCompatTextView.maxTemp(current: Current?){
    text = resources.getString(R.string.max_temp,current?.max ?: "-")
}

@BindingAdapter("setMinTemp")
fun AppCompatTextView.minTemp(current: Current?){
    text = resources.getString(R.string.max_temp,current?.min ?: "-")
}

@BindingAdapter("setHumidity")
fun AppCompatTextView.humidity(current: Current?){
    text = resources.getString(R.string.humidity_value,current?.humidity ?: "-")
}

@BindingAdapter("setHighPressure")
fun AppCompatTextView.pressure(current: Current?){
    text = resources.getString(R.string.pressure_value,current?.pressure ?: "-")
}

@BindingAdapter("setWind")
fun AppCompatTextView.wind(current: Current?){
    text = resources.getString(R.string.wind_speed,current?.wind ?: "-")
}
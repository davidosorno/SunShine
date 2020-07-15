package com.dog.sunshine.util

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.dog.sunshine.R
import java.io.IOException
import java.lang.NumberFormatException
import java.util.*

@BindingAdapter("setCurrentDate")
fun AppCompatTextView.currentDate(date: Date?){
    date?.let {
        text = setFormatDate(date, "EEE, MMM dd yyyy")
    }
}

@BindingAdapter("setHour")
fun AppCompatTextView.hour(date: Date){
    text = setFormatDate(date, "hh a")
}

@BindingAdapter("setHour")
fun AppCompatTextView.hour(date: Long){
    val formatDate = Date(date*1000) //I must add three 0 at the end of the number to get the right hour.
    text = setFormatDate(formatDate, "hh:mm a")
}

@BindingAdapter("setImage")
fun ImageView.image(icon: String?){
    icon?.let {
        val iconId = it.substring(0, 2)
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
@BindingAdapter("setStringValue")
fun AppCompatTextView.stringValue(stringValue: String?){
    text = resources.getString(R.string.weatherDescription, stringValue?.capitalize(Locale.US) ?: "")
}


@BindingAdapter("setTemp")
fun AppCompatTextView.temp(temp: Int){
    text = if(temp != 0){
        when(MEASUREMENT_UNIT){
            METRIC -> resources.getString(R.string.temp_value, temp)
            IMPERIAL -> {
                resources.getString(
                    R.string.temp_value,
                    getImperialTemp(temp)
                )
            }
            else -> throw IOException()
        }
    } else ""
}

//@BindingAdapter("setPressure")
//fun AppCompatTextView.pressure(pressure: Float){
//    text = resources.getString(R.string.pressure_value, pressure)
//}
//
//@BindingAdapter("setHumidity")
//fun AppCompatTextView.humidity(humidity: Int){
//    text = resources.getString(R.string.humidity_value, humidity)
//}
//
//@BindingAdapter("setWind")
//fun AppCompatTextView.wind(wind: Int){
//    text = resources.getString(R.string.wind_speed_value, wind)
//}

@BindingAdapter("setResource", "setValue")
fun AppCompatTextView.setValue(nameResource: String, value: Any){
    val resourceId = context.resourceIdByName(nameResource)
    val parameter = when(value) {
        is Int -> value
        is Float -> value
        else -> {
            throw NumberFormatException()
            return
        }
    }
    text = resources.getString(resourceId, parameter)
}
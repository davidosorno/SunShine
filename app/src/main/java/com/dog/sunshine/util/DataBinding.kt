package com.dog.sunshine.util

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.dog.sunshine.R
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

@BindingAdapter("nameResource", "setValue")
fun AppCompatTextView.setValue(stringResource: String, value: Int){
    val intResource = context.resourceIdByName(stringResource)
    text = if(value != 0) resources.getString(intResource, value) else ""
}

@BindingAdapter("nameResource", "setValue")
fun AppCompatTextView.setValue(stringResource: String, value: Float){
    val intResource = context.resourceIdByName(stringResource)
    text = if(value != 0F) resources.getString(intResource, value) else ""
}
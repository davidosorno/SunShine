package com.dog.sunshine.util

import android.content.ContentValues
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dog.sunshine.data.provider.WeatherContract
import com.dog.sunshine.data.weather.Weather
import com.google.android.material.snackbar.Snackbar

fun ContentValues.getStructure(item: ContentValues): Weather {
    return Weather(
        0L,
        item.getAsLong(WeatherContract.COLUMN_DATE),
        item.getAsLong(WeatherContract.COLUMN_WEATHER_ID),
        item.getAsInteger(WeatherContract.COLUMN_MAX_TEMP),
        item.getAsInteger(WeatherContract.COLUMN_MIN_TEMP),
        item.getAsInteger(WeatherContract.COLUMN_HUMIDITY),
        item.getAsInteger(WeatherContract.COLUMN_PRESSURE),
        item.getAsInteger(WeatherContract.COLUMN_WIND_SPEED),
        item.getAsInteger(WeatherContract.COLUMN_LOCATION_DEGREES)
    )
}

fun View.showSnackBar(msgId: Int){
    showSnackBar(resources.getString(msgId))
}

fun View.showSnackBar(msg: String){
    showSnackBar(
        msg,
        Snackbar.LENGTH_SHORT,
        null) {}
}

fun View.showSnackBar(
    msg: String,
    length: Int,
    actionMessage: Int?,
    action: (View) -> Unit
){
    val snackBar = Snackbar.make(this, msg, length)
    actionMessage?.let {

        snackBar.setAction(resources.getString(actionMessage)){
            action(this)
        }.show()
    }
}
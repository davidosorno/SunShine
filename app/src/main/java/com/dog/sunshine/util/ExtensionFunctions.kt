package com.dog.sunshine.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import com.google.android.material.snackbar.Snackbar

//fun ContentValues.getStructure(item: ContentValues): Weather {
//    return Weather(
//        0L,
//        item.getAsLong(WeatherContract.COLUMN_WEATHER_ID),
//        item.getAsInteger(WeatherContract.COLUMN_MAX_TEMP),
//        item.getAsInteger(WeatherContract.COLUMN_MIN_TEMP),
//        item.getAsInteger(WeatherContract.COLUMN_HUMIDITY),
//        item.getAsInteger(WeatherContract.COLUMN_PRESSURE),
//        item.getAsInteger(WeatherContract.COLUMN_WIND_SPEED),
//        item.getAsInteger(WeatherContract.COLUMN_LOCATION_DEGREES)
//    )
//}

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

fun Context.isInternetAvailable(): Boolean{
    var result = false
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val active = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when{
            active.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            active.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            active.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
    return result
}
package com.dog.sunshine.util

import java.text.SimpleDateFormat
import java.util.*

fun canLoadTodayWeather(date: Date?): Boolean{
    date?.let{
        val lastRecord: Long = date.time
        val today: Long = Calendar.getInstance().timeInMillis
        return today - lastRecord > 1800000 // Allows the app to update data at least 30 minutes after the last record
    }
    return true
}

fun setFormatDate(date: Date?, format: String):String{
//    val dateFormat = "EEE, MMM dd yyyy"
    val sdf = SimpleDateFormat(format, Locale.US)
    return date?.let { sdf.format(date) } ?: ""
}
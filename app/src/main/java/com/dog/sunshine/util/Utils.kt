package com.dog.sunshine.util

import java.text.SimpleDateFormat
import java.util.*

fun isTodayLoaded(date: Date): Boolean{
    return setFormatDate(date) == setFormatDate(Calendar.getInstance().time)
}

fun setFormatDate(date: Date?):String{

    val dateFormat = "MM/dd/yyyy"
    val sdf = SimpleDateFormat(dateFormat, Locale.US)
    return date?.let { sdf.format(date) } ?: ""
}
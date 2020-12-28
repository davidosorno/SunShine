package com.dog.sunshine.data

import androidx.room.TypeConverter
import com.dog.sunshine.data.weather.CurrentWeather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class Converters {
    @TypeConverter
    fun fromTimestampToDate(value: Long?): Date?{
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long?{
        return date?.time
    }

    @TypeConverter
    fun fromArrayCurrentToString(list: List<CurrentWeather>): String{
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToArrayCurrent(str: String): List<CurrentWeather>{
        val listType = object : TypeToken<List<CurrentWeather>>() {}.type
        return Gson().fromJson(str, listType)
    }
}


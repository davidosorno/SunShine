package com.dog.sunshine.data

import androidx.room.TypeConverter
import com.dog.sunshine.data.weather.current.Current
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
    fun fromArrayCurrentToString(list: List<Current>): String{
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToArrayCurrent(str: String): List<Current>{
        val listType = object : TypeToken<List<Current>>() {}.type
        return Gson().fromJson(str, listType)
    }
}


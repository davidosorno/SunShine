package com.dog.sunshine.data.networkservice

import androidx.annotation.NonNull
import androidx.room.Embedded

data class CurrentWeatherJsonObject(
    @NonNull
    val lat: Float,
    @NonNull
    val lon: Float,

    @Embedded
    val current: Current,

    @Embedded
    val daily: Array<TodayTemp>
)

data class Current(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Float,

    //Todo (This paramete has not been implemented yet)
    val feels_like: Float = 0F, //This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    val pressure: Float,
    val humidity: Float,

    //Todo (This paramete has not been implemented yet)
    val dew_point: Float = 0F, //Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form.

    //Todo (This paramete has not been implemented yet)
    val clouds: Float = 0F,
    //Todo (This paramete has not been implemented yet)
    val uvi: Float = 0F,
    //Todo (This paramete has not been implemented yet)
    val visibility: Long = 0L,

    val wind_speed: Float, //Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour

    //Todo (This paramete has not been implemented yet)
    val wind_gust: Float = 0F, //Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
    val wind_deg: Float, //Wind direction, degrees (meteorological) in format 360

    @Embedded
    val weather: Array<Description>,

    //Todo (This paramete has not been implemented yet)
    val rain: Float = 0F,

    //Todo (This paramete has not been implemented yet)
    val snow: Float = 0F
)

data class Description(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class TodayTemp(
    val temp: Temp
)

data class Temp(
    val morn: Float,
    val day: Float,
    val eve: Float,
    val night: Float,
    val min: Float,
    val max: Float
)

//
//import androidx.room.Embedded
//
//data class CurrentWeatherJsonObject(
//
//    @Embedded
//    val coord:  Coord,
//
//    @Embedded
//    val weather: Array<Description>,
//    val base:  String,
//
//    @Embedded
//    val main:  Main,
//    val visibility:  Long = 0L,
//
//    @Embedded
//    val wind:  Wind,
//
//    @Embedded
//    val clouds:  Clouds,
//
//    @Embedded
//    val sys:  Sys,
//    val timezone:  Long,
//
//    val id:  Long, //id City
//    val name:  String,
//    val cod:  Float
//)
//
//data class Coord(
//    val lon: Float,
//    val lat: Float
//)
//
//data class Main(
//    val temp: Float,
//    val feels_like: Float,
//    val temp_min: Float,
//    val temp_max: Float,
//    val pressure: Float,
//    val humidity: Float
//)
//
//data class Wind(
//    val speed: Float,
//    val deg: Float
//)
//
//data class Clouds(
//    val all: Float
//)
//
//data class Sys(
//    val id: Long,
//    val type: Int,
//    val country: String,
//    val sunrise: Long,
//    val sunset: Long
//)
//
//data class Description(
//    val id: Long,
//    val main: String,
//    val description: String,
//    val icon: String
//)
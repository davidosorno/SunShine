package com.dog.sunshine.data.networkservice

import androidx.annotation.NonNull
import androidx.room.Embedded
import kotlinx.android.parcel.RawValue

data class CurrentWeatherJsonObject(
    @NonNull
    val lat: Float,
    @NonNull
    val lon: Float,
    @Embedded
    val current: @RawValue CurrentWeatherMainData,
    @Embedded
    val hourly: @RawValue Array<CurrentWeatherMainData>,
    @Embedded
    val daily: @RawValue Array<Daily>
)

data class CurrentWeatherMainData(
    val dt: Long,
    val sunrise: Long = 0L,
    val sunset: Long = 0L,
    val temp: Float = 0F,
    val feels_like: Float = 0F, //This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    val pressure: Int,
    val humidity: Float,
    val dew_point: Float = 0F, //Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form.
    val clouds: Int = 0,
    val uvi: Float = 0F,
    val visibility: Long = 0L,
    val wind_speed: Float, //Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
    val wind_gust: Float = 0F, //Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
    val wind_deg: Int, //Wind direction, degrees (meteorological) in format 360
    @Embedded
    val weather: Array<Description>,
    val rain: Float = 0F,
    val snow: Float = 0F
)

data class Description(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class Daily(
    val dt: Long,
    val sunrise: Long = 0L,
    val sunset: Long = 0L,
    @Embedded
    val temp: Temp,
    @Embedded
    val feels_like: Temp,
    val pressure: Int,
    val humidity: Float,
    val dew_point: Float = 0F, //Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form.
    val clouds: Int = 0,
    val uvi: Float = 0F,
    val visibility: Long = 0L,
    val wind_speed: Float, //Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
    val wind_gust: Float = 0F, //Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
    val wind_deg: Int, //Wind direction, degrees (meteorological) in format 360
    @Embedded
    val weather: Array<Description>,
    val rain: Float = 0F,
    val snow: Float = 0F
)

data class Temp(
    val morn: Float,
    val day: Float,
    val eve: Float,
    val night: Float,
    val max: Float = 0F,
    val min: Float = 0F
)
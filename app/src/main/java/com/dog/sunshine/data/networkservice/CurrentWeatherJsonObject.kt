package com.dog.sunshine.data.networkservice

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class CurrentWeatherJsonObject(
    @NonNull
    val lat: Float,
    @NonNull
    val lon: Float,

    @Embedded
    val current: @RawValue MainData,

    @Embedded
    val hourly: @RawValue Array<MainData>,

    @Embedded
    val daily: @RawValue Array<Daily>
): Parcelable

data class MainData(
    val dt: Long,
    val sunrise: Long = 0L,
    val sunset: Long = 0L,
    val temp: Float = 0F,

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

data class Daily(
    val dt: Long,
    val sunrise: Long = 0L,
    val sunset: Long = 0L,

    @Embedded
    val temp: Temp,

    @Embedded
    val feels_like: Temp,

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

data class Temp(
    val morn: Float,
    val day: Float,
    val eve: Float,
    val night: Float,
    val min: Float = 0F,
    val max: Float = 0F
)
package com.dog.sunshine.data.networkservice

import androidx.room.Embedded

data class WeatherJsonObject(
    val id:  Long,

    @Embedded
    val coord:  Coord,

    @Embedded
    val weather: Array<Description>,
    val base:  String,

    @Embedded
    val main:  Main,
    val visibility:  Long = 0L,

    @Embedded
    val wind:  Wind,

    @Embedded
    val clouds:  Clouds,

    @Embedded
    val sys:  Sys,
    val timezone:  Long,

    val name:  String,
    val cod:  Float
)

data class Coord(
    val lon: Float,
    val lat: Float
)

data class Main(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Float,
    val humidity: Float
)

data class Wind(
    val speed: Float,
    val deg: Float
)

data class Clouds(
    val all: Float
)

data class Sys(
    val id: Long,
    val type: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

data class Description(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)
package com.dog.sunshine.util

import java.util.concurrent.Executors

const val DATABASE_NAME = "weather.db"
const val WEATHER_TABLE_NAME = "weather"

const val COORD_LAT = "lat"
const val COORD_LONG = "lon"

const val API_URL = "https://api.openweathermap.org/data/2.5/"

private val SINGLE_EXECUTOR = Executors.newSingleThreadExecutor()

fun executeThread(f: () -> Unit){
    SINGLE_EXECUTOR.execute(f)
}
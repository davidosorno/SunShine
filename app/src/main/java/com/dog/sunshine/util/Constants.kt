package com.dog.sunshine.util

import java.util.concurrent.Executors

const val DATABASE_NAME = "weather.db"
const val WEATHER_TABLE_NAME = "weather"

const val API_URL = "https://api.openweathermap.org/data/2.5/"

const val METRIC = "metric"
const val IMPERIAL = "imperial"
var MEASUREMENT_UNIT = METRIC

//The purpouse for this parameter and function is to test the application
private val SINGLE_EXECUTOR = Executors.newSingleThreadExecutor()

fun executeThread(f: () -> Unit){
    SINGLE_EXECUTOR.execute(f)
}
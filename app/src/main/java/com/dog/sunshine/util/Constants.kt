package com.dog.sunshine.util

import java.util.concurrent.Executors

const val DATABASE_NAME = "weather.db"
const val WEATHER_TABLE_NAME = "weather"

const val PREF_COORD_LAT = "coord_lat"
const val PREF_COORD_LONG = "coord_long"

private val SINGLE_EXECUTOR = Executors.newSingleThreadExecutor()

fun executeThread(f: () -> Unit){
    SINGLE_EXECUTOR.execute(f)
}
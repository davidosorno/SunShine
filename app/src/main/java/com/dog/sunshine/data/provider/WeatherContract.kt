package com.dog.sunshine.data.provider

import android.net.Uri

class WeatherContract {
    companion object{

        const val AUTHORITY = "com.dog.sunshine.data.provider"

        val BASE_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY")

        const val COLUMN_DATE = "date"

        /* Weather ID as returned by API, used to identify the icon to be used */
        const val COLUMN_WEATHER_ID = "weather_id"

        //TODO convert °C to °F
        /* Min and max temperatures in °C for the day (stored as floats in the database) */
        const val COLUMN_MIN_TEMP = "min"
        const val COLUMN_MAX_TEMP = "max"

        /* Humidity is stored as a float representing percentage */
        const val COLUMN_HUMIDITY = "humidity"

        /* Pressure is stored as a float representing percentage */
        const val COLUMN_PRESSURE = "pressure"

        /* Wind speed is stored as a float representing wind speed in mph */
        const val COLUMN_WIND_SPEED = "wind"

        /*
         * Degrees are meteorological degrees (e.g, 0 is north, 180 is south).
         * Stored as floats in the database.
         *
         * Note: These degrees are not to be confused with temperature degrees of the weather.
         */
        const val COLUMN_LOCATION_DEGREES = "degrees"
    }
}
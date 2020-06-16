package com.dog.sunshine.data.provider

import android.net.Uri

class WeatherContract {
    companion object{

        const val AUTHORITY = "com.dog.sunshine.data.provider"

        val BASE_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY")

        const val COLUMN_DATE = "date"

        const val COORD_LAT = "lat"
        const val COORD_LONG = "lon"

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
        const val COLUMN_WIND_DEGREES = "degrees"

        const val COLUMN_ICON_WEATHER = "icon"

        const val COLUMN_MAIN = "main"

        const val COLUMN_DESCRIPTION = "description"

        const val COLUMN_CITY_NAME = "city-name"

        const val COLUMN_SUNRISE_TIME = "sunrise"

        const val COLUMN_SUNSET_TIME = "sunset"
    }
}
package com.dog.sunshine.util

import android.content.ContentValues
import com.dog.sunshine.data.provider.WeatherContract
import com.dog.sunshine.data.weather.Weather

fun ContentValues.getStructure(item: ContentValues): Weather {
    return Weather(
        0L,
        item.getAsLong(WeatherContract.COLUMN_DATE),
        item.getAsLong(WeatherContract.COLUMN_WEATHER_ID),
        item.getAsInteger(WeatherContract.COLUMN_MAX_TEMP),
        item.getAsInteger(WeatherContract.COLUMN_MIN_TEMP),
        item.getAsInteger(WeatherContract.COLUMN_HUMIDITY),
        item.getAsInteger(WeatherContract.COLUMN_PRESSURE),
        item.getAsInteger(WeatherContract.COLUMN_WIND_SPEED),
        item.getAsInteger(WeatherContract.COLUMN_LOCATION_DEGREES)
    )
}


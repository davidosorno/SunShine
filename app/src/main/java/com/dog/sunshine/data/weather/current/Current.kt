package com.dog.sunshine.data.weather.current

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_CITY_NAME
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_DATE
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_DESCRIPTION
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_HUMIDITY
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_ICON_WEATHER
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_MAIN
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_MAX_TEMP
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_MIN_TEMP
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_PRESSURE
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_SUNRISE_TIME
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_SUNSET_TIME
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_WEATHER_ID
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_WIND_DEGREES
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_WIND_SPEED
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(indices = [
        Index(COLUMN_DATE)
    ]
)
@Parcelize
data class Current(
    @PrimaryKey
    val id: Int, //date in format ddd (day of the year)

    @ColumnInfo(name = COLUMN_DATE)
    val date: Date = Date(),

    @NonNull
    @ColumnInfo(name = COLUMN_WEATHER_ID)
    val weatherId: Long,

    @NonNull
    @ColumnInfo(name = COLUMN_MAX_TEMP)
    val max: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_MIN_TEMP)
    val min: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_HUMIDITY)
    val humidity: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_PRESSURE)
    val pressure: Float,

    @NonNull
    @ColumnInfo(name = COLUMN_WIND_SPEED)
    val wind: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_WIND_DEGREES)
    val winddegrees: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_ICON_WEATHER)
    val icon: String,

    @NonNull
    @ColumnInfo(name = COLUMN_MAIN)
    val main: String,

    @NonNull
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val description: String,

    @NonNull
    @ColumnInfo(name = COLUMN_CITY_NAME)
    val cityName: String,

    @NonNull
    @ColumnInfo(name = COLUMN_SUNSET_TIME)
    val sunset: Long,

    @NonNull
    @ColumnInfo(name = COLUMN_SUNRISE_TIME)
    val sunrise: Long
    ):Parcelable
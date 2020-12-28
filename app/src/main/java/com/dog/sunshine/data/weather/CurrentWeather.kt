package com.dog.sunshine.data.weather

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_CITY_NAME
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_CLOUDS
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_CURRENT_TEMP
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_DATE
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_DESCRIPTION
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_DEW_POINT
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_FEELS_LIKE
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_HUMIDITY
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_ICON_WEATHER
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_MAIN
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_MAX_TEMP
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_MIN_TEMP
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_PRESSURE
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_SUNRISE_TIME
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_SUNSET_TIME
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_UVI
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_VISIBILITY
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_WEATHER_ID
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_WIND_DEGREES
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_WIND_SPEED
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*
import kotlin.collections.ArrayList

@Entity(indices = [
        Index("id"),
        Index(COLUMN_DATE)
    ]
)
@Keep
@Parcelize
data class CurrentWeather(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = COLUMN_DATE)
    val date: Date = Date(),

    @NonNull
    @ColumnInfo(name = COLUMN_WEATHER_ID)
    val weatherId: Long = 0L,

    @NonNull
    @ColumnInfo(name = COLUMN_CURRENT_TEMP)
    val temp: Int = 0,

    @NonNull
    @ColumnInfo(name = COLUMN_FEELS_LIKE)
    val feelsLike: Int = 0,

    @NonNull
    @ColumnInfo(name = COLUMN_MAX_TEMP)
    val max: Int = 0,

    @NonNull
    @ColumnInfo(name = COLUMN_MIN_TEMP)
    val min: Int = 0,

    @NonNull
    @ColumnInfo(name = COLUMN_HUMIDITY)
    val humidity: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_PRESSURE)
    val pressure: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_DEW_POINT)
    val dewPoint: Float = 0F,

    @NonNull
    @ColumnInfo(name = COLUMN_CLOUDS)
    val clouds: Int = 0,

    @NonNull
    @ColumnInfo(name = COLUMN_UVI)
    val uvi: Float = 0F,

    @NonNull
    @ColumnInfo(name = COLUMN_VISIBILITY)
    val visibility: Long = 0L,

    @NonNull
    @ColumnInfo(name = COLUMN_WIND_SPEED)
    val wind: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_WIND_DEGREES)
    val winddegrees: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_ICON_WEATHER)
    val icon: String? = null,

    @NonNull
    @ColumnInfo(name = COLUMN_MAIN)
    val main: String,

    @NonNull
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val description: String,

    @NonNull
    @ColumnInfo(name = COLUMN_CITY_NAME)
    val cityName: String = "",

    @NonNull
    @ColumnInfo(name = COLUMN_SUNSET_TIME)
    val sunset: Long,

    @NonNull
    @ColumnInfo(name = COLUMN_SUNRISE_TIME)
    val sunrise: Long,

    val arrHourly: @RawValue List<CurrentWeather> = ArrayList(), //MainData

    val arrDaily: @RawValue List<CurrentWeather> = ArrayList() //Daily
    ):Parcelable
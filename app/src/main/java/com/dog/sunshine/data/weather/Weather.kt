package com.dog.sunshine.data.weather

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_DATE
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_HUMIDITY
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_LOCATION_DEGREES
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_MAX_TEMP
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_MIN_TEMP
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_PRESSURE
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_WEATHER_ID
import com.dog.sunshine.data.provider.WeatherContract.Companion.COLUMN_WIND_SPEED
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(indices = [
        Index(COLUMN_DATE)
    ]
)
@Parcelize
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @NonNull
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
    val pressure: Int,

    @NonNull
    @ColumnInfo(name = COLUMN_WIND_SPEED)
    val wind: Int,

    //TODO averiguar como se guardaaaaaa este valor
    @NonNull
    @ColumnInfo(name = COLUMN_LOCATION_DEGREES)
    val location: Int
):Parcelable
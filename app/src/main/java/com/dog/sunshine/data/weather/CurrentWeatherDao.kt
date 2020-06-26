package com.dog.sunshine.data.weather

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dog.sunshine.data.BasicOperationsDao
import com.dog.sunshine.data.provider.WeatherContract
import com.dog.sunshine.data.weather.CurrentWeather

@Dao
interface CurrentWeatherDao: BasicOperationsDao<CurrentWeather> {

    @Transaction
    @Query("SELECT * FROM currentweather ORDER BY date DESC")
    fun getAll(): DataSource.Factory<Int, CurrentWeather>

    @Transaction
    @Query("SELECT * FROM currentweather")
    fun getCurrentAndDaily(): LiveData<CurrentWeather>

    @Transaction
    @Query("SELECT * FROM currentweather WHERE ${WeatherContract.COLUMN_DATE} = :date")
    fun getByDate(date: Long): LiveData<List<CurrentWeather>>

    @Transaction
    @Query("DELETE FROM currentweather")
    fun deleteAll()



//    CURSOR FOR CONTENT PROVIDER

    @Transaction
    @Query("SELECT * FROM currentweather ORDER BY date DESC")
    fun getAllInCursor(): Cursor

    @Transaction
    @Query("SELECT * FROM currentweather WHERE ${WeatherContract.COLUMN_DATE} = :date")
    fun getByDateInCursor(date: Long): Cursor
}
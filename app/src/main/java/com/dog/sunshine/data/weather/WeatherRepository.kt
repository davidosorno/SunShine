package com.dog.sunshine.data.weather

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dog.sunshine.data.WeatherDB

class WeatherRepository(
    private val weatherDao: WeatherDao
) {

    companion object {
        @Volatile
        private var INSTANCE: WeatherRepository? = null

        fun getInstance(context: Context): WeatherRepository?{
            return INSTANCE ?: synchronized(WeatherRepository::class.java){
                if(INSTANCE == null){
                    val database = WeatherDB.getInstance(context)
                    INSTANCE = WeatherRepository(database.weatherDao())
                }
                return INSTANCE
            }
        }
    }

    fun getWeatherList(): LiveData<PagedList<Weather>>{
        return LivePagedListBuilder(
            weatherDao.getAll(), 4
        ).build()
    }
}

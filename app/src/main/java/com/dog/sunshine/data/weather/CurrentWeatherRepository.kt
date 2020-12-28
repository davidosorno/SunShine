package com.dog.sunshine.data.weather

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dog.sunshine.data.WeatherDB

class CurrentWeatherRepository(
    private val currentWeatherDao: CurrentWeatherDao
) {

    companion object {
        @Volatile
        private var INSTANCE: CurrentWeatherRepository? = null

        fun getInstance(context: Context): CurrentWeatherRepository?{
            return INSTANCE
                ?: synchronized(CurrentWeatherRepository::class.java){
                if(INSTANCE == null){
                    val database = WeatherDB.getInstance(context)
                    INSTANCE =
                        CurrentWeatherRepository(
                            database.weatherDao()
                        )
                }
                return INSTANCE
            }
        }
    }

    fun getWeatherList(): LiveData<PagedList<CurrentWeather>>{
        return LivePagedListBuilder(
            currentWeatherDao.getAll(), 4
        ).build()
    }

    fun getCurrentAndDaily(): LiveData<CurrentWeather>{
        return currentWeatherDao.getCurrentAndDaily()
    }

    fun insert(currentWeather: CurrentWeather): Long{
        currentWeatherDao.deleteAll()
        return currentWeatherDao.insert(currentWeather)
    }
}

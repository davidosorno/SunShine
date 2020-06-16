package com.dog.sunshine.data.weather.current

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dog.sunshine.data.WeatherDB

class CurrentRepository(
    private val currentDao: CurrentDao
) {

    companion object {
        @Volatile
        private var INSTANCE: CurrentRepository? = null

        fun getInstance(context: Context): CurrentRepository?{
            return INSTANCE
                ?: synchronized(CurrentRepository::class.java){
                if(INSTANCE == null){
                    val database = WeatherDB.getInstance(context)
                    INSTANCE =
                        CurrentRepository(
                            database.weatherDao()
                        )
                }
                return INSTANCE
            }
        }
    }

    fun getWeatherList(): LiveData<PagedList<Current>>{
        return LivePagedListBuilder(
            currentDao.getAll(), 4
        ).build()
    }

    fun insert(current: Current): Long{
        return currentDao.insert(current)
    }
}

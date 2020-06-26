package com.dog.sunshine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dog.sunshine.data.weather.CurrentWeather
import com.dog.sunshine.data.weather.CurrentWeatherDao
import com.dog.sunshine.util.DATABASE_NAME

@Database(entities = [
        CurrentWeather::class
    ],
    version = 1
)

@TypeConverters(Converters::class)
abstract class WeatherDB: RoomDatabase() {

    abstract fun weatherDao(): CurrentWeatherDao

    companion object{
        private var INSTANCE: WeatherDB? = null

        fun getInstance(context: Context): WeatherDB{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        WeatherDB::class.java,
                        DATABASE_NAME
                    )
                        .addCallback(object: Callback(){
                        }
                    )
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }

}
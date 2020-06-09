package com.dog.sunshine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dog.sunshine.data.weather.Weather
import com.dog.sunshine.data.weather.WeatherDao
import com.dog.sunshine.util.DATABASE_NAME
import com.dog.sunshine.util.executeThread
import java.util.*
import kotlin.collections.ArrayList

@Database(entities = [
        Weather::class
    ],
    version = 1
)

@TypeConverters(Converters::class)
abstract class WeatherDB: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

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
//                            override fun onCreate(db: SupportSQLiteDatabase) {
//                                super.onCreate(db)
//                                populateDateDB(instance!!)
//                            }
//
//                            override fun onOpen(db: SupportSQLiteDatabase) {
//                                super.onOpen(db)
//                                populateDateDB(instance!!)
//                            }
                        }
                    )
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }

        private fun populateDateDB(db: WeatherDB) {
            TODO("Not implemented.")
        }
    }

}
package com.dog.sunshine.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.data.weather.current.CurrentDao
import com.dog.sunshine.util.DATABASE_NAME

@Database(entities = [
        Current::class
    ],
    version = 1
)

@TypeConverters(Converters::class)
abstract class WeatherDB: RoomDatabase() {

    abstract fun weatherDao(): CurrentDao

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